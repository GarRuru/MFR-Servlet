
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

public class EvaluateData {
	@SerializedName("Transfer_id")
	private int TID;
	@SerializedName("Driver_id")
	private int DUserID;
	@SerializedName("Passenger_id")
	private int PUserID;
	@SerializedName("DPEvaluate")
	private String[] DPE;	//司機給乘客的評價
	@SerializedName("PDEvaluate")
	private String[] PDE;	//乘客給司機的評價
	
	public EvaluateData(int TID,int DID,int PID)
	{
		this.TID = TID;
		this.DUserID = DID;
		this.PUserID = PID;
		this.DPE = new String[2];
		this.PDE = new String[2];
	}
	

}
