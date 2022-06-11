package com.example.whatsapp.api;

import com.example.whatsapp.entities.*;
import com.google.gson.JsonObject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.*;

public interface WebServiceAPI {
    @GET("api/contacts")
    Call<List<ApiContact>> GetAllContacts(@Query("username") String username);

    @POST("api/contacts")
    Call<Void> CreateContact(@Body ContactsPostRequest contact, @Query("username") String username);

    @GET("api/contacts/{contactId}")
    Call<ApiContact> GetContact(@Path("contactId") int contactId, @Query("username") String username);

    @PUT("api/contacts/{contactId}")
    Call<Void> EditContact(@Body ContactsPutRequest contact, @Path("contactId") int contactId, @Query("username") String username);

    @DELETE("api/contacts/{contactId}")
    Call<Void> DeleteContact(@Path("contactId") int contactId, @Query("username") String username);

    @POST("api/transfer")
    Call<Void> PostTransfer(@Body ApiTransfer transfer);

    @POST("api/invitations")
    Call<Void> PostInvitation(@Body ApiInvitation invitation);

    @GET("api/contacts/{contactId}/messages")
    Call<List<ApiMessage>> GetAllContactMessage(@Path("contactId") int contactId, @Query("username") String username);

    @GET("api/contacts/{contactId}/messages/{messageId}")
    Call<ApiMessage> GetMessageById(@Path("contactId") int contactId, @Path("messageId") int messageId, @Query("username") String username);

    @DELETE("api/contacts/{contactId}/messages/{messageId}")
    Call<Void> DeleteMessage(@Path("contactId") int contactId, @Path("messageId") int messageId, @Query("username") String username);

    @POST("api/contacts/{contactId}/messages")
    Call<Void> CreateMessage(@Body MessagePostRequest content, @Path("contactId") int contactId, @Query("username") String username);

    @PUT("api/contacts/{contactId}/messages/{messageId}")
    Call<Void> EditMessageById(@Path("contactId") int contactId, @Path("messageId") int messageId, @Query("username") String username, @Body MessagePostRequest content);

    @POST("Login")
    Call<User> Login(@Body LoginPostRequest loginPostRequest);
}