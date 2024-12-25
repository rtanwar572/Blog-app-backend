package com.rohit.blog.payloads;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class PostResponse {
    private List<PostDTO> content;
    private int pageNumber;
    private int pageSize;
    private long totalRecords;
    private int totalPages;
    private boolean isLastPage;
}
