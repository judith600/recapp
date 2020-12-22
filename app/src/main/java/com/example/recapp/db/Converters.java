package com.example.recapp.db;

import androidx.room.TypeConverter;

import java.net.URI;

public class Converters {

    @TypeConverter
    public static String uriToString(URI uri) {
        return  uri == null ? null : uri.toString();
    }

    @TypeConverter
    public static URI stringToUri(String str) {
        return str == null ? null : URI.create(str);
    }
}
