package Services;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.HashMap;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.mime.TypedFile;


public interface Service {
    @FormUrlEncoded
    @POST("/api/v2.0/party/user_affiliation")
    void affiliateWithParty(@Header("Authorization") String str, @Field("partyId") int partyId, Callback<JsonElement> callback);

    @POST("/data")
    void transaction(@Query("id") String i, Callback<JsonElement> callback );

    @GET("/complaintmanagement/complaint/verify/{na}/{cnic}")
    void verifycnic(@Path("na") String i, @Path("cnic") String x, Callback<JsonElement> callback);
    //MynewApi

    @GET("/complaintmanagement/team/listnames/{na}/{party}")
    void getCategories(@Path("na") String i, @Path("party") String x, Callback<JsonArray> callback);

    @POST("/complaintmanagement/complaint/save/file/{party}/{category}")
    @Multipart
    void postpicture(@Part("file") TypedFile typedFile, @Path("party") String y, @Path("category") String p, @Part("cnic") String cnic, @Part("complaintText") String comptext, @Part("phoneNumber") String phonenumb, Callback<JsonElement> callback);

    @GET("/complaintmanagement/complaint/track/{complaintId}")
    void getcomplainstatus(@Path("complaintId") String x, Callback<JsonObject> callback);

    @POST("/complaintmanagement/complaint/save/{party}/{category}")
    void submitcomplain(@Path("party") String i, @Path("category") String x, @Body HashMap b, Callback<JsonElement> callback);

    @GET("/complaintmanagement/complaint/list")
    void getComplaints(@Header("Authorization") String str, Callback<JsonArray> callback);




    @PUT("/api/v2.0/manage/approve/user/{userId}")
    void approveUserByAdmin(@Header("Authorization") String str, @Path("userId") int i, Callback<JsonObject> callback);

    @POST("/api/v2.0/user/change/password")
    @Multipart
    void changePassword(@Header("Authorization") String str, @Part("oldPassword") String oldPassword, @Part("newPassword") String newPassword, @Part("confirmPassword") String confirmPassword, Callback<JsonElement> callback);

    @POST("/api/v2.0/user/change/picture")
    @Multipart
    void changeUserProfilePicture(@Header("Authorization") String str, @Part("file") TypedFile typedFile, Callback<JsonElement> callback);

    @GET("/api/v2.0/auth/cnic/{CNIC}")
    void checkUniquenessOfCNIC(@Path("CNIC") String str, Callback<JsonElement> callback);

    @GET("/api/v2.0/auth/email/{email}")
    void checkUniquenessOfEmail(@Path("email") String str, Callback<JsonElement> callback);

    @GET("/api/v2.0/auth/phone/{phoneNumber}")
    void checkUniquenessOfPhone(@Path("phoneNumber") String str, Callback<JsonElement> callback);

    @GET("/api/v2.0/user")
    void currentUserData(@Header("Authorization") String str, Callback<JsonElement> callback);

    @PUT("/api/v2.0/manage/deactivate/{userId}")
    void deactivateUserByAdmin(@Header("Authorization") String str, @Path("userId") int i, Callback<JsonObject> callback);

    @GET("/api/v2.0/role")
    void getAllRoles(Callback<JsonElement> callback);

    @GET("/api/v2.0/city/list")
    void getCities(Callback<JsonElement> callback);

    @GET("/api/v2.0/country/list")
    void getCountries(Callback<JsonElement> callback);

    @GET("/api/v2.0/voter/{voterId}")
    void getDetailsOfVoter(@Path("voterId") int i, Callback<JsonElement> callback);

    @GET("/api/v2.0/manage/users")
    void getListOfActiveUsers(@Header("Authorization") String str, Callback<JsonObject> callback);

    @GET("/api/v2.0/block")
    void getListOfBlocks(@Header("Authorization") String str, @Query("page") int page, @Query("size") int size, Callback<JsonElement> callback);

    @GET("/api/v2.0/manage/deactive/users")
    void getListOfInactiveUsers(@Header("Authorization") String str, Callback<JsonObject> callback);

    @GET("/api/v2.0/manage/pending/user")
    void getListOfPendingUsers(@Header("Authorization") String str, Callback<JsonObject> callback);

    @GET("/api/v2.0/block/{blockCode}")
    void getListOfVoter(@Header("Authorization") String str, @Path("blockCode") int blockCode, @Query("page") int page, @Query("size") int size, Callback<JsonElement> callback);

    @GET("/api/v2.0/local")
    void getLocalConstituency(Callback<JsonElement> callback);

    @GET("/api/v2.0/voter/{id}")
    void getMarkedVoter(@Header("Authorization") String str, @Path("id") int i, Callback<JsonElement> callback);

    @GET("/api/v2.0/voter/getMarkedVoter/{id}")
    void getMarkedVoterData(@Header("Authorization") String str, @Path("id") int i, Callback<JsonElement> callback);

    @GET("/api/v2.0/national")
    void getNationalConstituency(Callback<JsonElement> callback);

    @GET("/api/v2.0/state")
    void getStateConstituency(Callback<JsonElement> callback);

    @GET("/api/v2.0/state/all")
    void getStates(Callback<JsonElement> callback);

    @GET("/api/v2.0/voter/family/voter_slip")
    void getVoterFamilySlip(@Header("Authorization") String str, @Query("page") int page, @Query("size") int size, Callback<JsonElement> callback);

    @GET("/api/v2.0/voter/{voterID}")
    void getVotersFamilyList(@Header("Authorization") String accessToken, @Path("voterID") int voterID, Callback<JsonElement> callback);

    @GET("/api/v2.0/voter/voter_slip")
    void getVoterSlip(@Header("Authorization") String str, Callback<JsonElement> callback);

    @GET("/api/v2.0/voter/voter_slip/{voterID}")
    void getVoterSlip(@Header("Authorization") String str, @Path("voterID") int voterID, Callback<JsonElement> callback);

    @GET("/api/v2.0/party/list")
    void listOfParties(Callback<JsonElement> callback);

    @GET("/api/v2.0/role")
    void listOfRoles(Callback<JsonElement> callback);

    @GET("/api/v2.0/voter/voter_status")
    void listOfStatus(Callback<JsonElement> callback);

    @Headers({"Authorization:Basic c29jb2w6c2VjcmV0"})
    @Multipart
    @POST("/oauth/token")
    void login(@Part("username") String str, @Part("password") String str2, @Part("grant_type") String str3, Callback<JsonElement> callback);

    @POST("/api/v2.0/auth/register")
    void registerUser();

    @PUT("/api/v2.0/manage/reject/{userId}")
    void rejectUserByAdmin(@Header("Authorization") String str, @Path("userId") int i, Callback<JsonObject> callback);

    @PUT("/api/v2.0/role/change")
    @FormUrlEncoded
    void requestAPartyRole(@Header("Authorization") String str, @Field("roleId") long roleId, Callback<JsonElement> callback);

    @POST("/api/v2.0/auth/forget/password")
    @Multipart
    void resetPassword(@Part("email") String str, Callback<JsonElement> callback);


    @PUT("/api/v2.0/user/self_voter_status")
    @FormUrlEncoded
    void updateSelfStatus(@Header("Authorization") String str, @Field("statusId") long statusId, Callback<JsonElement> callback);

    @PUT("/api/v2.0/manage/user")
    @FormUrlEncoded
    void updateUserPasswordByAdmin(@Header("Authorization") String str, @Field("password") String str2, @Field("userId") int i, Callback<JsonObject> callback);

    @PUT("/api/v2.0/voter/updatemarkedvoter")
    @FormUrlEncoded
    void updateVoterStatus(@Field("voterId") long j, @Field("statusId") int statusId, @Header("Authorization") String str, Callback<JsonElement> callback);

    @PUT("/api/v2.0/voter/updatemarkedvoter")
    @FormUrlEncoded
    void updateVotersSocialInfo(@Header("Authorization") String str, @Field("facebookUrl") String str2, @Field("linkedinUrl") String str3, @Field("mobileNumber") String str4, @Field("note") String str5, @Field("twitterUrl") String str6, @Field("voterId") int i, @Field("whatsappNumber") String str7, @Field("statusId") int i2, Callback<JsonElement> callback);


    @PUT("/api/v2.0/voter/updatemarkedvoter")
    @FormUrlEncoded
    void updateVoterMemberInterest(@Field("voterId") long j, @Field("statusId") int statusId, @Field("member") boolean member, @Header("Authorization") String str, Callback<JsonElement> callback);


    @GET("/api/v2.0/party/worker/activity/{userId}")
    void workerActivityReport(@Header("Authorization") String str, @Path("userId") int i, Callback<JsonElement> callback);

    @GET("/api/v2.0/voter/search/{code}/block")
    void searchBlock(@Header("Authorization") String access_token, @Path("code") String code, @Query("page") int page, @Query("size") int size, Callback<JsonElement> callback);

    @POST("/api/v2.0/voter/living/{voterId}/{statusId}")
    void setLivingStatus(@Header("Authorization") String access_token, @Path("voterId") int voterId, @Path("statusId") int statusId, Callback<JsonElement> callback);

    @PUT("/api/v2.0/voter/living/{voterId}/{statusId}")
    void updateLivingStatus(@Header("Authorization") String access_token, @Path("voterId") int voterId, @Path("statusId") int statusId, Callback<JsonElement> callback);

    @GET("/api/v2.0/voter/living/status")
    void listOfLivingStatus(@Header("Authorization") String access_token, Callback<JsonElement> callback);

    @GET("/api/v2.0/voter/voter_slip/cnic/{cnic}")
    void voterSlipCnic(@Header("Authorization") String access_token, @Path("cnic") String cnic, Callback<JsonElement> callback);

}
