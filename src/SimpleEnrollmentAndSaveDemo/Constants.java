package SimpleEnrollmentAndSaveDemo;
import java.util.UUID;

public class Constants {
    public static final String ApplicationPackage = "com.example.rayhanulmasud.onlymatch";
    public static final String ApplicationTag = "fingerPrintScanner";
    public static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
    public static final String DEVICE_MAC_ADDRESS = "20:15:11:02:56:80";

    public static final String HTTP_RESPONSE_STATUS_NOT_ACCEPTABLE = "406";
    public static final String HTTP_RESPONSE_STATUS_OK = "200";
    public static final String HTTP_RESPONSE_STATUS_PROCESSING = "102";
    public static final String HTTP_RESPONSE_STATUS_UNAUTHORIZED = "401";
    public static final String HTTP_RESPONSE_STATUS_BAD_REQUEST = "400";
    public static final String HTTP_RESPONSE_STATUS_ACCEPTED = "202";

    public static final String BASE_URL_GET = "209.126.69.250";
    public static final String BASE_URL_POST = "http://209.126.69.250:8080";
    public static final int BASE_URL_GET_PORT = 8080;
    public static final String BASE_URL_GET_PATH = "/contact/rest/fp/";

    public static final String URL_ENROLL = "enroll/get";
    public static final String URL_DELETE_ALL = "/contact/simulate/restart";
    public static final String URL_DELETE_ONE = "/contact/clear/user";
    public static final String URL_SUBMIT_LOGIN = "get";
    public static final String URL_BATCH_LOGIN_OFFLINE_DATA = "/contact/rest/json/post";

    public static final String COMMAND_ENROLL_USER = "COMMAND_ENROLL_USER";
    public static final String COMMAND_BATCH_PROCESSING_IDENTIFY = "COMMAND_BATCH_PROCESSING_IDENTIFY";
    public static final String COMMAND_DELETE_ONE_USER = "COMMAND_DELETE_ONE_USER";
    public static final String COMMAND_DELETE_ALL_USER = "COMMAND_DELETE_ALL_USER";
    public static final String COMMAND_SUBMIT_LOGIN = "COMMAND_SUBMIT_LOGIN";
    public static final String COMMAND_IDENTIFY = "IDENTIFY";
    public static final String COMMAND_FP_CANCEL = "FP_CANCEL";
    public static final String SUCCESS = "SUCCESS";
    public static final String FAIL = "FAIL";
    public static final int MESSAGE_WRITE = 1;
    public static final int MESSAGE_READ = 2;
    public static final String WORK_COMMAND_ENROLL = "ENROLL";
    public static final String WORK_COMMAND_IDENTIFY = "IDENTIFY";
    public static final String WORK_COMMAND_CLEAR_ALL_TEMPLATE = "CLEAR_ALL";
    public static final String WORK_COMMAND_CLEAR_ONE_TEMPLATE = "CLEAR_TEMPLATE";

    // Main Activity Messages
    public static final String VERIFYING_RELEASE_FINGER = "VERIFYING... RELEASE FINGER";
    public static final String VERIFIED_ID = "VERIFIED ID: ";
    public static final String REOPEN_THE_APPLICATION = "RE-OPEN THE APPLICATION";
    public static final String NO_MATCH = "NO MATCH!";
    public static final String BAD_QUALITY = "BAD QUALITY!";
    public static final String ERROR_RELEASE_YOUR_FINGER = "RELEASE YOUR FINGER....ERROR!!";
    public static final String ERROR_NO_MATCHED_FINGERPRINT = "NO MATCHING FINGER PRINT FOUND!!";
    public static final String ERROR_TRY_AGAIN = "ERROR! TRY AGAIN PLEASE.";

    // Admin Activity Messages
    public static final String CHECK_BT_CONNECTION_AND_TRY_AGAIN = "CHECK BLUETOOTH CONNECTION AND TRY AGAIN!";
    public static final String DUPLICATE_ID = "DUPLICATE ID!";
    public static final String DUPLICATE_FINGER_PRINT = "DUPLICATE FINGER PRINT!";
    public static final String ENROLL_SUCCESS = "ENROLL SUCCESSFUL!";
    public static final String ENROLLED_ID = "SUCCESS! ENROLL ID: ";
    public static final String PLACE_FINGER_TESTING_1 = "PLACE YOUR FINGER. TESTING(1)";
    public static final String PLACE_FINGER_TESTING_2 = "PLACE YOUR FINGER. TESTING(2)";
    public static final String PLACE_FINGER_TESTING_3 = "PLACE YOUR FINGER. TESTING(3)";
    public static final String PLACE_FINGER_BAD_QUALITY = "BAD QUALITY. PLACE FINGER AGAIN";
    public static final String TIME_OUT = "TIME OUT";
    public static final String RELEASE_FINGER = "RELEASE YOUR FINGER";
    public static final String ENROLLMENT_FAILED = "ENROLLMENT FAILED. TRY AGAIN";
    public static final String TEMPLATE_LIMIT_EXCEED = "TEMPLATE LIMIT EXCEEDS";
    public static final String NUMBER_OF_DELETED_TEMPLATES = "NUMBER OF DELETED TEMPLATES: ";
    public static final String DELETED_TEMPLATE_ID = "DELETED TEMPLATE ID: ";
    public static final String FAILED_TO_CLEAR_ALL_TEMPLATES = "FAILED TO CLEAR TEMPLATES";
    public static final String INVALID_ENROLL_ID = "INVALID ENROLL ID";
    public static final String ID_NOT_ENROLLED_YET = "ID NOT ENROLLED YET";
    public static final String TEMPLATES_EMPTY = "NO FINGERPRINT ENROLLED YET";
    public static final String ERROR_ENROLL = "COULD NOT ENROLL. TRY AGAIN";
    public static final String ERROR_DELETE_TEMPLATE = "COULD NOT DELETE TEMPLATE. TRY AGAIN";

    public static String[] AT_COMMAND_FOR_IDENTIFY_FREE = {"0x55", "0xAA", "0x25", "0x01", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x25", "0x01"};

    public static String[] AT_COMMAND_FOR_FP_CANCEL = {"0x55", "0xAA", "0x30", "0x01", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x30", "0x01"};
    public static String[] RESPONSE_FIRST_FOR_FP_CANCEL = {"0xAA", "0x55", "0x03", "0x01", "0x04", "0x00",
    		"0x01", "0x00", 
            "0x41", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x49", "0x01"};
    
    public static String[] RESPONSE_SECOND_FOR_FP_CANCEL = {"0xAA", "0x55", "0x30", "0x01", "0x04", "0x00",
    		"0x00", "0x00", 
            "0x00", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x34", "0x01"};

    public static String[] AT_COMMAND_FOR_IDENTIFY_ONE = {"0x55", "0xAA", "0x02", "0x01", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x02", "0x01"};

    public static String[] AT_COMMAND_FOR_ENROLL_RIGHT = {"0x55", "0xAA", "0x03", "0x01", "0x02", "0x00", "0x61",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x66", "0x01"};
    
    public static String[] AT_COMMAND_FOR_ENROLL_AT_ONE = {"0x55", "0xAA", "0x03", "0x01", "0x02", "0x00", "0x01",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x06", "0x01"};
    
    public static String[] RESPONSE_ASKING_FOR_FIRST_FP_ENROLL_AT_ONE = {"0xAA", "0x55", "0x03", "0x01", "0x04", "0x00",
    		"0x00", "0x00", 
            "0xF1", "0xFF", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0xF7", "0x02"};
    
    public static String[] RESPONSE_ASKING_FOR_SECOND_FP_ENROLL_AT_ONE = {"0xAA", "0x55", "0x03", "0x01", "0x04", "0x00",
    		"0x00", "0x00", 
            "0xF2", "0xFF", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0xF8", "0x02"};
    
    public static String[] RESPONSE_ASKING_FOR_THIRD_FP_ENROLL_AT_ONE = {"0xAA", "0x55", "0x03", "0x01", "0x04", "0x00",
    		"0x00", "0x00", 
            "0xF3", "0xFF", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0xF9", "0x02"};
    
    public static String[] RESPONSE_FOR_FP_ACCEPTED_ENROLL_AT_ONE = {"0xAA", "0x55", "0x03", "0x01", "0x04", "0x00",
    		"0x00", "0x00", 
            "0xF4", "0xFF", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0xFA", "0x02"};
        
    public static String[] RESPONSE_FOR_ENROLL_FINISHED_AT_ONE = {"0xAA", "0x55", "0x03", "0x01", "0x06", "0x00",
    		"0x00", "0x00", 
            "0x01", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x0A", "0x01"};
    
    public static String[] RESPONSE_FOR_TEMPLATE_EXISTS_FOR_ONE = {"0xAA", "0x55", "0x03", "0x01", "0x04", "0x00",
    		"0x01", "0x00", 
            "0x14", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x1C", "0x01"};
    
    public static String[] RESPONSE_FOR_BAD_FP_QUALITY = {"0xAA", "0x55", "0x03", "0x01", "0x04", "0x00",
    		"0x01", "0x00", 
            "0x21", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x29", "0x01"};
    
    public static String[] RESPONSE_FOR_FAILED_TO_GENERALIZE = {"0xAA", "0x55", "0x03", "0x01", "0x04", "0x00",
    		"0x01", "0x00", 
            "0x30", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x38", "0x01"};
     
    public static String[] AT_COMMAND_FOR_CLEAR_TEMPLATE_AT_ONE = {"0x55", "0xAA", "0x05", "0x01", "0x02", "0x00", "0x01",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x08", "0x01"};
    

    public static String[] RESPONSE_SUCCESS_FOR_CLEAR_TEMPLATE_AT_ONE = {"0xAA", "0x55", "0x05", "0x01", "0x04", "0x00",
    		"0x00", "0x00", 
            "0x01", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x0A", "0x01"};

    public static String[] RESPONSE_FAILURE_FOR_CLEAR_TEMPLATE_AT_ONE = {"0xAA", "0x55", "0x05", "0x01", "0x04", "0x00",
    		"0x01", "0x00", 
            "0x13", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x1D", "0x01"};
        
    public static String[] AT_COMMAND_FOR_ENROLL_LEFT = {"0x55", "0xAA", "0x03", "0x01", "0x02", "0x00", "0x61",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x66", "0x01"};
    
    public static String[] AT_COMMAND_FOR_READ_TEMPLATE_AT_ONE = {"0x55", "0xAA", "0x0A", "0x01", "0x02", "0x00", "0x01",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x0D", "0x01"};
    
    public static String[] RESPONSE_SUCCESS_FOR_READ_TEMPLATE_AT_ONE = {"0xAA", "0x55", "0x0A", "0x01", "0x04", "0x00",
    		"0x00", "0x00", 
            "0xF4", "0x01", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x03", "0x02"};
    
    public static String[] RESPONSE_FAILURE_FOR_READ_TEMPLATE_AT_ONE = {"0xAA", "0x55", "0x0A", "0x01", "0x04", "0x00",
    		"0x01", "0x00", 
            "0x13", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x22", "0x01"};

    public static String[] AT_COMMAND_FOR_CLEAR_ONE_TEMPLATE = {"0x55", "0xAA", "0x05", "0x01", "0x02", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x06", "0x01"};    

    public static String[] AT_COMMAND_CLEAR_ALL_TEMPLATE = {"0x55", "0xAA", "0x06", "0x01", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x06", "0x01"};
    
    public static String[] AT_COMMAND_LED_ON = {"0x55", "0xAA", "0x24", "0x01", "0x02", "0x00", "0x01",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x27", "0x01"};
    
    public static String[] AT_COMMAND_LED_OFF = {"0x55", "0xAA", "0x24", "0x01", "0x02", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x26", "0x01"};
    
    public static String[] RESPONSE_FOR_LED_ON_OFF = {"0xAA", "0x55", "0x24", "0x01", "0x04", "0x00",
    		"0x00", "0x00", 
            "0x00", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x00", "0x00", "0x00", "0x00", "0x00",
            "0x28", "0x01"};   
    
}
