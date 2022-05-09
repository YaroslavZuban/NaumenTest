import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class СacheServer {
    private int cacheAmount = 0;
    private ArrayList<Integer> dataList;
    private int number = 0;
    private int maxRequests = 1;
    private int numberRequest = 1;
    private Map<Integer, Integer> data;

    public СacheServer(String nameFile) throws IOException {
        data = new HashMap<>();
        readFile(nameFile);
    }

    public void dataWork() {
        ArrayList<Integer> arrayList = new ArrayList<>(dataList);

        for (int i = 0; i < dataList.size(); i++) {
            Integer key = dataList.get(i);

            if (cacheAmount == maxRequests) {
                elementValidation(arrayList);
            }

            if (data.get(key) != null) {
                arrayList.remove(key);
            } else {
                data.put(key, 0);
                number++;
                arrayList.remove(key);
                cacheAmount++;
                elementValidation(arrayList);
            }
        }

        System.out.println(number);
    }

    private void elementValidation(ArrayList<Integer> arrayList) {
        Iterator<Map.Entry<Integer, Integer>> entries = data.entrySet().iterator();

        while (entries.hasNext()) {
            Map.Entry<Integer, Integer> entry = entries.next();
            Integer key = entry.getKey();

            if (!arrayList.contains(key)) {
                cacheAmount--;
                data.remove(key);
                break;
            }
        }
    }

    public void writingFile() throws IOException {
        FileWriter fw = new FileWriter("output.txt", false);
        fw.write(String.valueOf(number));
        fw.close();
    }

    private void readFile(String nameFile) throws IOException {
        int i = 0;
        String line;

        File file = new File(nameFile);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        while ((line = br.readLine()) != null) {
            if (i == 0) {
                i++;
                String[] s2_array = line.split("\\D+");

                maxRequests = Integer.parseInt(s2_array[0]);
                numberRequest = Integer.parseInt(s2_array[1]);

                if (0 >= maxRequests && maxRequests > Math.pow(10, 5) || 0 >= numberRequest && numberRequest > Math.pow(10, 5)) {
                    throw new IOException("String can not be empty!");
                }

                dataList = new ArrayList<>(numberRequest);

                continue;
            }

            Integer key = Integer.parseInt(line);
            dataList.add(key);
        }

        br.close();
        fr.close();
    }
}
