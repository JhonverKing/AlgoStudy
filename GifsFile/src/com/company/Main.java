package com.company;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        String filename;

        CreateGIFS("hosts_access_log_00.txt");

    }

    public static void CreateGIFS(String fileName) throws IOException {
        List<String> gifList = new ArrayList<String>();
        List<String> split = new ArrayList<String>();

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

            //출력 파일 생성
            fileWriter = new FileWriter("gifs_" + fileName) ;
            bufWriter = new BufferedWriter(fileWriter);

            // 읽기
            while ((line = bufReader.readLine()) != null) {

                String timestamp = line.substring(line.indexOf("[")+1, line.lastIndexOf("]"));
                String[] spt_request = line.substring(line.indexOf("\"")+1, line.lastIndexOf("\"")).split("/");

                // timestamp, request 제거
                String tmp = line;
                tmp = tmp.substring(0, tmp.indexOf("[")).concat(tmp.substring(tmp.lastIndexOf("]")+2));
                tmp = tmp.substring(0, tmp.indexOf("\"")).concat(tmp.substring(tmp.lastIndexOf("\"")+2));

                String[] spt_line = tmp.split(" ");

                System.out.println("line : " + line);
                System.out.println("timestamp : " + timestamp);
                System.out.println("tmp : " + tmp);


                if( spt_request[0].contains("GET") && spt_line[3].contains("200"))
                {
                    for(int i=0; i<spt_request.length; i++){
                        if(spt_request[i].toLowerCase().contains(".gif")){
                            bufWriter.write(spt_request[i].trim().split(" ")[0]);
                            bufWriter.newLine(); //버퍼에 개행 삽입
                        }
                    }
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

