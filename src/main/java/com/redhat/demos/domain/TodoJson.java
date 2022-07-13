package com.redhat.demos.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public record TodoJson(String title, int order, boolean completed) {
}
