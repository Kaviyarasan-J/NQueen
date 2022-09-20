import java.util.*;

class Solutions {
    public static int GRID;
    public List<List<String>>output;
    public String str=new String();
    
    public static boolean checkRow(int row,List<String> board){
        for(int i=0;i<GRID;i++){
            if(board.get(row).charAt(i)=='Q'){
                return true;
            }
        }
        return false;
    }
    public static boolean checkCol(int col,List<String> board){
        for(int i=0;i<GRID;i++){
            if(board.get(i).charAt(col)=='Q'){
                return true;
            }
        }
        return false;
    }
    public static int[] getCros1(int row,int col){
        while(row>0&&col>0&&row<GRID&&col<GRID){
            col--;
            row--;
        }
        int[] arr=new int[3];
        arr[0]=row;
        arr[1]=col;
        return arr;
    }
    public static int[] getCros2(int row,int col){
        while(row>0&&col>0&&row<GRID-1&&col<GRID-1){
            col--;
            row++;
        }
        int[] arr=new int[3];
        arr[0]=row;
        arr[1]=col;
        return arr;
    }
    public static boolean checkCross1(int row,int col,List<String> board){
        int[] arr=getCros1(row,col);
        row=arr[0];
        col=arr[1];
        while(row<GRID&&col<GRID&&row>=0&&col>=0){
            if(board.get(row).charAt(col)=='Q'){
                return true;
            }
            row++;
            col++;
        }
        return false;
    }
    public static boolean checkCross2(int row,int col,List<String> board){
        int[] arr=getCros2(row,col);
        row=arr[0];
        col=arr[1];
        while(row>=0&&col>=0&&row<GRID&&col<GRID){
            if(board.get(row).charAt(col)=='Q'){
                return true;
            }
            row--;
            col++;
        }
        return false;
    }
    public static boolean checkPlacement(int row,int col,List<String>board){
        return !checkRow(row,board)&&!checkCol(col,board)&&!checkCross1(row,col,board)&&!checkCross2(row,col,board);
    }
    public static String getpos(int ind){
        char[] arr=new char[GRID];
        for(int i=0;i<GRID;i++){
            if(i==ind){
                arr[i]='Q';
            }
            else{
                arr[i]='.';
            }
        }
        String str=new String(arr);
        return str;
    }
    public void setResult(List<List<String>>output,Solutions s) {
    	s.output=output;
    }
    public static boolean solve(List<String>board,List<List<String>>output,Solutions s){
        int count=0;
        for(int i=0;i<GRID;i++){
            for(int j=0;j<GRID;j++){
                if(checkRow(i,board)){
                     if(i==GRID-1){
                            output.add(board);
                            s.setResult(output,s);
                            printBoard(board,s);
                            return false;
                        }
                    break;
                }
                else{
                    if(checkPlacement(i,j,board)){
                        board.set(i,getpos(j));
                        if(solve(board,output,s)){
                            return true;
                        }
                        else{
                            board.set(i,s.str);
                            count++;
                        }
                    }
                    else{
                        count++;
                    }
                }
            }
            if(count==GRID){
                return false;
            }
        }
        return true;
    }
    public static void printBoard(List<String> board,Solutions s){
        for(List<String> ls:s.output) {
        	 for(String s1:ls){
                 System.out.println(s1);
             }
        	 System.out.println();
        }
        System.out.println();   
    }
    public static void solveNQueens(int n) {
    	Solutions s=new Solutions();
    	List<String>board=new ArrayList<>();
    	List<List<String>>output=new ArrayList<>();
        GRID=n;
        s.iniString(s);
        board=s.freeboard(board,s);
        solve(board,output,s);
    }
    public List<String> freeboard(List<String> board,Solutions s){
        board=new ArrayList<>();
        for(int i=0;i<GRID;i++){
            board.add(s.str);
        }
        return board;
    }
    public void iniString(Solutions s){
        char[] arr=new char[GRID];
        for(int i=0;i<GRID;i++){
            arr[i]='.';
        }
        s.str=new String(arr);
    }
}