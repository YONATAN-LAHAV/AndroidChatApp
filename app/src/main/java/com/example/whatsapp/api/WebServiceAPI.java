package com.example.whatsapp.api;
import com.example.whatsapp.entities.*;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.*;

public interface WebServiceAPI {
    @GET("contacts")
    Call<List<ApiContact>> GetAllContacts(@Query("username") String username);

    @POST("contacts")
    Call<Void> CreateContact(@Body ContactsPostRequest contact, @Query("username") String username);

    @GET("contacts/{contactId}")
    Call<ApiContact> GetContact(@Path("contactId") int contactId, @Query("username") String username);

    @PUT("contacts/{contactId}")
    Call<Void> EditContact(@Body ContactsPutRequest contact, @Path("contactId") int contactId, @Query("username") String username);

    @DELETE("contacts/{contactId}")
    Call<Void> DeleteContact(@Path("contactId") int contactId, @Query("username") String username);

    @POST("transfer")
    Call<Void> PostTransfer(@Body ApiTransfer transfer);

    @POST("invitations")
    Call<Void> PostInvitation(@Body ApiInvitation invitation);

    @GET("contacts/{contactId}/messages")
    Call<List<ApiMessage>> GetAllContactMessage(@Path("contactId") int contactId, @Query("username") String username);

    @GET("contacts/{contactId}/messages/{messageId}")
    Call<ApiMessage> GetMessageById(@Path("contactId") int contactId, @Path("messageId") int messageId,  @Query("username") String username);

    @DELETE("contacts/{contactId}/messages/{messageId}")
    Call<Void> DeleteMessage(@Path("contactId") int contactId, @Path("messageId") int messageId,  @Query("username") String username);

    @POST("contacts/{contactId}/messages")
    Call<Void> CreateMessage(@Body MessagePostRequest content, @Path("contactId") int contactId, @Query("username") String username);

    @PUT("contacts/{contactId}/messages/{messageId}")
    Call<Void> EditMessageById(@Path("contactId") int contactId, @Path("messageId") int messageId,  @Query("username") String username, @Body MessagePostRequest content);
}