package com.stormnet.server.dao.xml.db;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class XmlDataBase {

    private static final XmlDataBase database = new XmlDataBase();

    public static XmlDataBase getDatabase() {
        return database;
    }

    private static final String ALL_DB_TABLES_FILE_PATH = "course-db/db-tables.xml";

    private Map<String, XmlTable> allDbTables = new HashMap<>();

    private Document allTablesDocument;

    public XmlDataBase() {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            allTablesDocument = documentBuilder.parse(ALL_DB_TABLES_FILE_PATH);

            NodeList allTablesTags = allTablesDocument.getElementsByTagName("db-table");
            int tagCount = allTablesTags.getLength();

            for (int i = 0; i <tagCount ; i++) {
                Element tableTag = (Element) allTablesTags.item(i);

                String tableName = tableTag.getAttribute("name");
                String tableXmlFile = tableTag.getAttribute("table-file");

                Element maxIdValueTag = (Element) tableTag.getElementsByTagName("max-id-value").item(0);
                String maxIdStr = maxIdValueTag.getTextContent();
                long maxId = Long.parseLong(maxIdStr);

                Document tableDocument = documentBuilder.parse(tableXmlFile);

                XmlTable xmlTable = new XmlTable(tableName,tableXmlFile,maxId,tableDocument);

                allDbTables.put(tableName, xmlTable);
            }
        } catch (Exception e) {
            throw new XmlDatabaseException(e);
        }
    }

    public Document getTableDocument(String tableName){
        return allDbTables.get(tableName).getDocument();
    }

    public void saveDbTableDocument(String tableName) throws TransformerException {
        XmlTable table = allDbTables.get(tableName);
        Document document = table.getDocument();
        saveXmlDocument(document, table.getXmlFilePath());
    }

    public Long getNexIdForTable(String tableName) throws TransformerException {
        XmlTable table = allDbTables.get(tableName);
        Long nextId = table.getNextId();

        NodeList allTableTags = allTablesDocument.getElementsByTagName("db-table");
        int tablesCount = allTableTags.getLength();
        for (int i = 0; i < tablesCount ; i++) {
            Element dbTableTag = (Element) allTableTags.item(i);
            String nameFromXml = dbTableTag.getAttribute("name");

            if (nameFromXml.equals(tableName)) {
                Element maxIdTag = (Element) dbTableTag.getElementsByTagName("max-id-value").item(0);
                maxIdTag.setTextContent(nextId.toString());
                break;
            }
        }
        saveXmlDocument(allTablesDocument, ALL_DB_TABLES_FILE_PATH);

        return nextId;
    }

    private void saveXmlDocument(Document document, String filePath) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File(filePath));
        transformer.transform(source, result);
    }
}
