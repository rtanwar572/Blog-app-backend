package com.rohit.blog.payloads;

import lombok.Data;

@Data
public class FileResponse {
    String path;
    String message;
    PostDTO Post;

    public FileResponse(String fileName, String s,PostDTO updatePost) {
        this.path=fileName;
        this.message=s;
        this.Post=updatePost;
    }
}
