package com.stormnet.server.dao.xml.db;

public class XmlDatabaseException extends RuntimeException {

    public XmlDatabaseException(Throwable cause) {
        super("Errors occurred during access to the Data Base:", cause);
    }
}
