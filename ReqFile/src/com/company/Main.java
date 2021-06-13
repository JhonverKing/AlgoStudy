package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        CreateReqFile("hosts_access_log_00.txt");

    }
    public static void CreateReqFile(String fileName) throws IOException {
        List<String> timeStampList = new ArrayList<String>();

        // 입력파일
        FileReader filereader = null;
        BufferedReader bufReader = null;

        // 출력파일
        FileWriter fileWriter = null;
        BufferedWriter bufWriter = null;
        try {
            //입력 버퍼 생성
            filereader = new FileReader(fileName);
            bufReader = new BufferedReader(filereader);
            String line = "";

            // 읽기
            while ((line = bufReader.readLine()) != null) {
                String timestamp = line.substring(line.indexOf('\"')+1, line.lastIndexOf('\"')-6);
                timeStampList.add(timestamp);
            }


            // 카운트
            HashMap<String, Integer> timeStampCount = new HashMap<String, Integer>();
            for(int i=0; i<timeStampList.size(); i++){
                if(!timeStampCount.containsKey(timeStampList.get(i))){
                    timeStampCount.put(timeStampList.get(i), 1);
                }
                else{
                    timeStampCount.put(timeStampList.get(i), timeStampCount.get(timeStampList.get(i))+1);
                }
            }

            //출력 파일 생성
            fileWriter = new FileWriter("req_" + fileName) ;
            bufWriter = new BufferedWriter(fileWriter);
            for(String key : timeStampCount.keySet() ){
                if(timeStampCount.get(key) > 1){
                    bufWriter.write(key);
                    bufWriter.newLine(); //버퍼에 개행 삽입
                }
            }
            bufWriter.flush(); //버퍼의 내용을 파일에 쓰기

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            bufReader.close();
            filereader.close();
            bufWriter.close();
            fileWriter.close();
        }
    }

}
