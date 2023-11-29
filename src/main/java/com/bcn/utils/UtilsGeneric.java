package com.bcn.utils;

import java.sql.Timestamp;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.Types;
import java.text.DecimalFormat;

import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;

@Component
public class UtilsGeneric {

    public JsonObject getJsonObject(ResultSet resultSet)throws Exception {
		JsonObject object=null;
		try {
            object = new JsonObject();
            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
//				System.out.println(resultSet.getMetaData().getColumnLabel(i).toLowerCase()+" >> ("+resultSet.getMetaData().getColumnType(i )+") == "+resultSet.getObject(i).toString());
				switch(resultSet.getMetaData().getColumnType(i)) {
					case Types.FLOAT: case Types.DOUBLE: case Types.NUMERIC: case Types.DECIMAL:
		            	object.addProperty(resultSet.getMetaData().getColumnLabel(i).toLowerCase(), 
		                		(resultSet.getObject(i)==null ? 0.0 : new DecimalFormat("###,###,###,###.##").parse(resultSet.getObject(i).toString())));
		            break;
					case Types.INTEGER: case Types.BIGINT: case Types.SMALLINT:
		            	object.addProperty(resultSet.getMetaData().getColumnLabel(i).toLowerCase(), 
		                		(resultSet.getObject(i)==null ? 0 : Integer.parseInt(resultSet.getObject(i).toString())));
		            break;
					case Types.BOOLEAN:
						object.addProperty(resultSet.getMetaData().getColumnLabel(i).toLowerCase(), Boolean.parseBoolean(resultSet.getObject(i).toString()));
					break;
					case Types.TIMESTAMP:
						object.addProperty(resultSet.getMetaData().getColumnLabel(i).toLowerCase(), 
						(resultSet.getObject(i)==null ? "" : resultSet.getObject(i).toString()));
					default:
		            	object.addProperty(resultSet.getMetaData().getColumnLabel(i).toLowerCase(), 
		                		(resultSet.getObject(i)==null ? "" : resultSet.getObject(i).toString()));
				}
            }
            return object;
		}catch(Exception ex) {
			ex.printStackTrace();
			throw new Exception(ex);
		}
	}

	public String getMd5(String input) //funcion para generar contraseña md5
    {
        try { 
            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
 
            // digest() method is called to calculate message digest
            // of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());
 
            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);
 
            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
 
        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
	
	public Timestamp getFechaHoy(){//obtiene la fecha del día
		Long datetime = System.currentTimeMillis();
		return new Timestamp(datetime);
	}

}
