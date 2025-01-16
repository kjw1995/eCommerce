package com.kjw.ecommerce.common.url.CommonUrl;

public class CommonURL {

	public static final String API_VERSION = "/api/v1";
	public static final String AUTH_URI = "/auth";
	public static final String VIEW_URI = "/view";

	public static final String MEMBER_URI = "/members";
	public static final String PRODUCT_URI = "/product";

	/* 인증 */
	public static final String MEMBER_LOGIN = API_VERSION + AUTH_URI + MEMBER_URI + "/login";
	public static final String MEMBER_LOGOUT = API_VERSION + AUTH_URI + MEMBER_URI + "/logout";

	/* 페이지 */
	public static final String VIEW_MAIN = VIEW_URI + "/main";
	public static final String VIEW_LOGIN = VIEW_URI + "/login";
	public static final String VIEW_REGISTRATION = VIEW_URI + "/registration";
	public static final String VIEW_DASHBOARD = VIEW_URI + "/dashboard";
	public static final String VIEW_PRODUCT = VIEW_URI + PRODUCT_URI;

}
