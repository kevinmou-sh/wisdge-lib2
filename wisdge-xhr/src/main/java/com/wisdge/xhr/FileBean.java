package com.wisdge.xhr;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String filename;
	private byte[] data;
}
