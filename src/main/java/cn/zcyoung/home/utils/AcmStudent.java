package cn.zcyoung.home.utils;

public class AcmStudent{
	private String no;
	private String name;
	private String[] Atime;//每道题ac的时间
	private int[] Ecount;//每道题提交错几次
	private int ACount;//ac题目数目
	private String TotalTime;//总时间
	
	public String getMess(){
		String tm = "";
		for(int i = 0; i < Atime.length; i++){
			tm += (char) ('A' + i) + ": ";
			if(Atime[i] == null || Atime[i].equals("")) tm += "没做\n";
			else tm += Atime[i] + "(" + Ecount[i] + ")\n";
		}
		
		return no + " " + name + "共做出" + ACount +"题，总用时" + TotalTime + "\n" + tm;
	}
	
	public int getACount() {
		return ACount;
	}
	public void setACount(int aCount) {
		ACount = aCount;
	}
	public String getTotalTime() {
		return TotalTime;
	}
	public void setTotalTime(String totalTime) {
		TotalTime = totalTime;
	}
	public int[] getEcount() {
		return Ecount;
	}
	public void setEcount(int[] qcount) {
		Ecount = qcount;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getAtime() {
		return Atime;
	}
	public void setAtime(String[] qtime) {
		Atime = qtime;
	}

}




