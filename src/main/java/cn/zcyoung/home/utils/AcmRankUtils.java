package cn.zcyoung.home.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AcmRankUtils {
	static String path = "C:\\Users\\zc\\Desktop\\文件夹\\acm\\周赛\\1\\第一次周赛\\实时排名.html";

	public static String getHtml(String path){
		try{
			String html = "", line;
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
			while((line = br.readLine()) != null){
				html += line + "\n";
			}
			br.close();
			return html;
		}catch(Exception e){e.printStackTrace();}
		return null;
	}

	public static int getQesCount(String  html){
		Pattern pt = Pattern.compile("(?<=<th class=\"thContest\">)([\\s\\S]{1,110})(?=</DIV></a></th>)");
		Matcher mc = pt.matcher(html);
		int no = 0;
		while(mc.find()){
			no++;
			//System.out.println(mname.group());
		}
		return no;
	}

	public static List<AcmStudent> getListStudent(String html, int len){
		List<AcmStudent> list = new ArrayList<AcmStudent>();
		Pattern pt = Pattern.compile("(?<=<tr>)(\r\n\r\n<td><DIV align=center>[\\s\\S]{660,1300})(?=</tr>)");
		Matcher mc = pt.matcher(html);
		while(mc.find()){
			String shtml = mc.group();
			if(shtml.contains("CONTENTEDITABLE")){
				AcmStudent acmStudent = getStudent(shtml, len);
				if(!acmStudent.getNo().startsWith("14") && !acmStudent.getNo().startsWith("13")) list.add(acmStudent);
			}
		}
		return list;
	}

	private static AcmStudent getStudent(String shtml, int qtCount) {
		try{
			//学号
			AcmStudent student = new AcmStudent();
			String[] at = new String[qtCount];
			int[] et = new int[qtCount];
			int noInedx = shtml.indexOf("userName=");
			student.setNo(shtml.substring(noInedx + 9, noInedx + 9 + 10));
			//姓名
			int nameIndex = shtml.indexOf("<a href=\"/acmhome/userDetail.do?s=1&amp;userName="+ student.getNo() +"\">");
			String tmp = shtml.substring(nameIndex + 10);
			int tt = tmp.indexOf(student.getNo());
			tmp = tmp.substring(tt + 12);
			String name = "";
			for(int i = 0; tmp.charAt(i) != '<'; i++)
				name += tmp.charAt(i);
			student.setName(name);
			//成绩
			Pattern pt = Pattern.compile("(?<=<td><DIV align=center><span CONTENTEDITABLE=true>&nbsp;)([\\s\\S]{0,50})(?=</span></DIV></td>)");
			Matcher mc = pt.matcher(tmp);
			int n = 0;
			while(mc.find()){
				String fstr = mc.group();
				if(n == 0) {}
				else if(n == 1) student.setACount(Integer.parseInt(fstr.trim()));
				else if(n == 2) student.setTotalTime(fstr.trim());
				else{
					int in = fstr.indexOf("(");
					if(in == -1) {
						at[n - 3] = fstr.trim();
						et[n - 3] = 0;
					}else{
						at[n - 3] = fstr.substring(0, in).trim();
						int rin = fstr.indexOf(")");
						et[n - 3] = Integer.parseInt(fstr.substring(in + 1, rin));
					}
				}
				n++;
			}
			student.setAtime(at);
			student.setEcount(et);
			return student;
		}catch(Exception e){e.printStackTrace();}
		return null;
	}

	/*public static void main(String[] args){
		String html = getHtml(path);
		int len = getQesCount(html);
		List<Student> listStudent = getListStudent(html, len);
		for(Student st: listStudent){
			try{System.out.println("('" + st.getNo() + "',0,'" + st.getName() + "')," );
			}catch(Exception e){e.printStackTrace();}
			
		}
		System.exit(0);
	}*/
}

