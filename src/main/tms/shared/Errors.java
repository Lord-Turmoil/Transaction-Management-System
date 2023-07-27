/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.shared;

public class Errors {
	public static final String IllegalArgumentCount = "Illegal argument count";
	public static final String AlreadyLoggedIn = "Already logged in";
	public static final String NotLoggedIn = "Please log in first";
	public static final String NoSuchUser = "Kakafee number not exists";
	public static final String IllegalId = "Illegal Kakafee number";
	public static final String DuplicatedId = "Kakafee number exists";
	public static final String IllegalName = "Illegal name";
	public static final String IllegalPassword = "Illegal password";
	public static final String PasswordInconsistent = "Passwords do not match";
	public static final String WrongPassword = "Wrong password";
	public static final String IllegalIdentity = "Illegal identity";
	public static final String PermissionDenied = "Permission denied";
	public static final String UserNotMerchant = "Kakafee number does not belong to a Merchant";
	public static final String IllegalShopName = "Illegal shop name";
	public static final String IllegalShopId = "Illegal shop id";
	public static final String NoSuchShopId = "Shop id not exists";
	public static final String DuplicatedShopName = "shop name already exists";

	public static final String IllegalProductName = "Illegal commodity name";
	public static final String IllegalProductId = "Illegal commodity id";
	public static final String IllegalProductPrice = "Illegal commodity price";
	public static final String IllegalProductCount = "Illegal commodity count";
	public static final String NoSuchProductId = "Commodity id not exists";

	private Errors() {}
}
