package ru.itis.rasimusv.managers;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EntityManager {
    private final DataSource dataSource;

    public EntityManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public <T> void createTable(String tableName, Class<T> entityClass) {
        StringBuilder request = new StringBuilder("CREATE TABLE " + tableName + " (");
        Field[] fields = entityClass.getDeclaredFields();
        for (Field field : fields) {
            if(!field.isAccessible()){
                field.setAccessible(true);
            }
        }
        for (int i = 0; i < fields.length; i++) {
            String sqlTypeName = "";

            switch (fields[i].getType().getSimpleName()) {

                case "String":
                    sqlTypeName = "VARCHAR";
                    break;

                case "long":
                case "Long":
                    sqlTypeName = "BIGINT";
                    break;

                case "int":
                case "Integer":
                    sqlTypeName = "INTEGER";
                    break;

                case "float":
                case "Float":
                    sqlTypeName = "FLOAT";
                    break;

                case "BigDecimal":
                    sqlTypeName = "DECIMAL";
                    break;

                case "boolean":
                case "Boolean":
                    sqlTypeName = "BIT";
                    break;

                case "byte":
                case "Byte":
                    sqlTypeName = "TINYINT";
                    break;

                case "short":
                case "Short":
                    sqlTypeName = "SMALLINT";
                    break;

                case "double":
                case "Double":
                    sqlTypeName = "DOUBLE";
                    break;

                case "byte[]":
                case "Byte[]":
                    sqlTypeName = "LONGVARBINARY";
                    break;

                case "Date":
                    sqlTypeName = "DATE";
                    break;

                case "Time":
                    sqlTypeName = "TIME";
                    break;

                case "Timestamp":
                    sqlTypeName = "TIMESTAMP";
                    break;
            }

            request.append(fields[i].getName()).append(" ").append(sqlTypeName);

            if ((i + 1) != fields.length) {
                request.append(", ");
            } else {
                request.append(");");
            }
        }

        try {
            dataSource.getConnection().prepareStatement(String.valueOf(request)).executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void save(String tableName, Object entity) {
        Class<?> classOfEntity = entity.getClass();

        StringBuilder request = new StringBuilder("INSERT INTO " + tableName + " (");
        Field[] fields = classOfEntity.getDeclaredFields();
        for (Field field : fields) {
            if(!field.isAccessible()){
                field.setAccessible(true);
            }
        }
        for (int i = 0; i < fields.length; i++) {

            request.append(fields[i].getName());

            if ((i + 1) != fields.length) {
                request.append(", ");
            } else {
                request.append(") VALUES (");
            }
        }
        for (int i = 0; i < fields.length; i++) {
            try {
                String sqlStyle;
                if (String.class.equals(fields[i].getType())) {
                    sqlStyle = "'" + fields[i].get(entity) + "'";
                } else if (Boolean.class.equals(fields[i].getType()) || boolean.class.equals(fields[i].getType())) {
                    if (fields[i].get(entity).equals("true")) {
                        sqlStyle = "B'1'";
                    }else {
                        sqlStyle = "B'0'";
                    }
                } else {
                    sqlStyle = String.valueOf(fields[i].get(entity));
                }
                request.append(sqlStyle);
            } catch (IllegalAccessException e) {
                throw new IllegalStateException(e);
            }

            if ((i + 1) != fields.length) {
                request.append(", ");
            } else {
                request.append(");");
            }
        }

        System.out.println(request.toString());

        try {
            dataSource.getConnection().prepareStatement(String.valueOf(request)).executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public <T, ID> T findById(String tableName, Class<T> resultType, Class<ID> idType, ID idValue) {
        StringBuilder request = new StringBuilder("SELECT ");

        Field[] fields = resultType.getDeclaredFields();
        for (Field field : fields) {
            if(!field.isAccessible()){
                field.setAccessible(true);
            }
        }
        for (int i = 0; i < fields.length; i++) {

            request.append(fields[i].getName());

            if ((i + 1) != fields.length) {
                request.append(", ");
            }
        }
        request.append(" FROM ").append(tableName).append(" WHERE id = ").append(idValue.toString()).append(";");

        ResultSet resultSet;
        try {
            resultSet = dataSource.getConnection().prepareStatement(String.valueOf(request)).executeQuery();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

        T entity;
        try {
            entity = resultType.getConstructor().newInstance();
            for(Field field: fields) {
                String name = field.getName();
                String value = resultSet.getString(name);
                field.set(entity, field.getType().getConstructor(String.class).newInstance(value));
            }
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | SQLException e) {
            throw new IllegalStateException(e);
        }

        return entity;
    }
}