package com.sparta.finalpractice.store.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class StoreRegisterRequestDto {

    @NotBlank
    private String name;

    private String introduce;

    @NotBlank
    private String category;

}
