package com.mcann.constant;

public class RestApis {
	private static final String VERSION = "/v1";
	private static final String API = "/api";
	private static final String DEVELOPER = "/dev";
	private static final String TEST = "/test";
	private static final String PROD = "/prod";
	
	private static final String ROOT = VERSION + DEVELOPER;
	
	
	public static final String USER = ROOT + "/user";
	public static final String CARD = ROOT + "/card";
	public static final String STATION = ROOT + "/station";
	public static final String LINE = ROOT + "/line";
	public static final String TRANSACTION = ROOT + "/transaction";
	
	public static final String ADD = "/add";
	public static final String DELETE = "/delete";
	public static final String FINDALL = "/find-all";
	public static final String FINDALLVWUSERCARDS = "find-all-vw-user-cards";
	public static final String FINDALLVWNOTUSERCARDS = "find-all-vw-not-user-cards";
	public static final String FINDALLVWSTANDARDCARDS = "find-all-vw-standard-cards";
	public static final String FINDALLVWSTUDENTCARDS = "find-all-vw-student-cards";
	public static final String FINDALLVWTEACHERCARDS = "find-all-vw-teacher-cards";
	public static final String FINDALLVWELDERLYCARDS = "find-all-vw-elderly-cards";
	public static final String FINDALLVWDISCOUNTEDCARDS = "find-all-vw-discounted-cards";
	public static final String FINDALLPASSIVECARDS = "find-all-vw-passive-cards";
	public static final String FINDALLACTIVECARDS = "find-all-vw-active-cards";
	public static final String FINDALLRESPONSE = "/find-all-response";
	public static final String UPDATE = "/update";
	public static final String DISABLED = "/disabled";
	public static final String FINDBYID = "/find-by-id";
	public static final String ADDUSER = "/add-user";
	
	public static final String GETALL = "/get-all-users";
	public static final String GETALLCARDS = "get-all-cards";
	
	public static final String POSTADDCARD = "post-add-card";
	public static final String FINDALLBYUSERNAME = "/find-all-by-user-name";
	public static final String FINDBYUSERID = "/find-by-id";
	public static final String REGISTER = "/register";
	public static final String ADDSTATION = "/add-station";
	public static final String GETCARDUSAGE = "/get-card-usage";
	public static final String GETALLTRANSACTION = "/get-all-transaction";
}