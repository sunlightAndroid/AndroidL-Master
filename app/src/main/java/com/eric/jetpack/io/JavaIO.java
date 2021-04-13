package com.eric.jetpack.io;

import java.io.*;

/**
 * @Author: eric
 * @CreateDate: 3/25/21 5:56 PM
 * @Description: java类作用描述
 */
public class JavaIO {
    public static void main(String[] args) {
//        io1();
//        io2();
//        io3();
//        io4();
//        io5();

        copy();
    }

    static void io1() {
        OutputStream stream = null;
        try {
            stream = new FileOutputStream("./text.txt");
            stream.write('a');
            stream.write('b');
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static void io2() {
        InputStream stream = null;
        try {
            stream = new FileInputStream("./text.txt");
            System.out.println((char) stream.read());
            System.out.println((char) stream.read());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static void io3() {
        InputStream stream = null;
        Reader inputStreamReader = null;
        try {
            stream = new FileInputStream("./text.txt");
            inputStreamReader = new InputStreamReader(stream);
            System.out.println((char) inputStreamReader.read());
            System.out.println((char) inputStreamReader.read());
            System.out.println((char) inputStreamReader.read());
            System.out.println((char) inputStreamReader.read());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    static void io4() {
        InputStream stream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            stream = new FileInputStream("./text.txt");
            inputStreamReader = new InputStreamReader(stream);
            bufferedReader = new BufferedReader(inputStreamReader);
            System.out.println(bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static void io5() {
        OutputStream stream = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            stream = new FileOutputStream("./text.txt");
            outputStreamWriter = new OutputStreamWriter(stream);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write("____abcdef");
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stream != null) {
                    stream.close();
                }
                if (outputStreamWriter != null) {
                    outputStreamWriter.close();
                }
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 文件复制
     * <p>
     * 先读进来，然后写出去
     */
    static void copy() {
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            inputStream = new FileInputStream("./text.txt");
            outputStream = new FileOutputStream("./new_text.txt");

            byte[] data = new byte[1024];
            int read;
            while ((read = inputStream.read(data)) != -1) {
                outputStream.write(data, 0, read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
