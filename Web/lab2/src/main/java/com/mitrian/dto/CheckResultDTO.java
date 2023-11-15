package com.mitrian.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckResultDTO {
	public int x;
	public float y;
	public float r;
	public boolean result;
	public String resultString;
}
