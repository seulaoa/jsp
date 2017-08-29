package servlet;

public class Exam {
	String str;
	
	public static void main(String[] args) {
		 Exam e = new Exam();
		
		 System.out.println(e.str);
		 try {
			 System.out.println(e.str.toUpperCase());
		 } catch(Exception e1){
			 System.out.println("에러가 났어요.");
		 }
		 
	}
}
