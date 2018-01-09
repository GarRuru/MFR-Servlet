
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;


public class FreeRideData {
	@SerializedName("Transfer_id")
	private int TransferID;
	@SerializedName("Request_User_id")
	private int RUserID;
	@SerializedName("Driver_User_id")
	private int DUserID;
	@SerializedName("Free_ride_Position")
	private double[] FRPos;
	@SerializedName("Dest_Position")
	private double[] DestPos;
	@SerializedName("Request_time")
	private int[] time;
	@SerializedName("Status")
	private String stat;
	@SerializedName("Request_Note")
	private String RRNote;
	@SerializedName("Driver_Reject_Note")
	private String DriverRNote;
	
	public void setTID(int id)
	{
		TransferID = id;
	}
	public int getTID()
	{
		return TransferID;
	}
	public void setRUserID(int id)
	{
		RUserID = id;
	}
	public int getRUserID()
	{
		return RUserID;
	}
	public void setDUserID(int id)
	{
		DUserID = id;
	}
	public int getDUserID()
	{
		return DUserID;
	}
	//TODO : Some get/set Method aren't complete.
}
