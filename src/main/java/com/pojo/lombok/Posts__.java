package com.pojo.lombok;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Posts__ {
    private int id;
    private String title;
    private String author;
    private String platform;
}
