package com.springBoot.ContactManagement.Helper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageHelper {

	private String messageContent;
	private String typeString;
}
