package com.igor040897.dinosaurs.API.Request;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by fanre on 11/5/2017.
 */
@JsonObject
public class LoadImageRequest {

    @JsonField(name = "filename")
    private String filename;

    @JsonField(name = "target_uri")
    private String targetUri;

    @JsonField(name = "filemime")
    private String filemime;

    @JsonField(name = "file")
    private String file;

    @JsonField(name = "filesize")
    private String filesize;

    LoadImageRequest(){}

    public LoadImageRequest(String filename, String targetUri, String filemime, String file, String filesize) {
        this.filename = filename;
        this.targetUri = targetUri;
        this.filemime = filemime;
        this.file = file;
        this.filesize = filesize;
    }
}
