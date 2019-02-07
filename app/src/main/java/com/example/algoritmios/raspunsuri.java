package com.example.algoritmios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;

public class raspunsuri extends AppCompatActivity {
    TextView raspuns;

    String ref_len1, frames1;
    int ref_len, frames;
    int pointer = 0, hit = 0, fault = 0;
    int buffer[];
    int reference[], reference1[];
    int mem_layout[][];
    int index[];
    ArrayList<Integer> stack = new ArrayList<Integer>();
    int used_layout[][];
    Boolean isFull = false;

    private static String removeLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raspunsuri);
        raspuns = (TextView) findViewById(R.id.raspunsul);
        raspuns.setKeyListener(null);
        fifo();
        lru();
        optimal();
        clock();
    }

    void fifo() {

        String aux = pagini.pages1.getText().toString();
        ref_len1 = MainActivity.pages_no.getText().toString();
        frames1 = MainActivity.frames_no.getText().toString();
        ref_len = Integer.parseInt(ref_len1);
        frames = Integer.parseInt(frames1);
        reference = new int[ref_len];
        reference1 = new int[ref_len];
        mem_layout = new int[ref_len][frames];
        buffer = new int[frames];
        int i = 0, j;
        char ch;

        for (j = 0; j < frames; j++)
            buffer[j] = -1;

        for (i = 0; i < ref_len; i++) {
            ch = aux.charAt(i);
            reference[i] = ch - '0';
        }


        for (i = 0; i < ref_len; i++) {
            int search = -1;
            for (j = 0; j < frames; j++) {
                if (reference[i] == buffer[j]) {
                    search = j;
                    hit++;
                    break;
                }
            }
            if (search == -1) {
                buffer[pointer] = reference[i];
                fault++;
                pointer++;
                if (pointer == frames)
                    pointer = 0;
            }
            for (j = 0; j < frames; j++)
                mem_layout[i][j] = buffer[j];
        }
        raspuns.append("FIFO:");
        raspuns.append("\n");
        for (i = 0; i < frames; i++) {
            for (j = 0; j < ref_len; j++)
                raspuns.append(mem_layout[j][i] + " ");
            raspuns.append("\n");
        }

        raspuns.append("\n");
        raspuns.append("The number of Hits: " + hit);
        raspuns.append("\n");
        raspuns.append("Hit Ratio: " + (float) ((float) hit / ref_len));
        raspuns.append("\n");
        raspuns.append("The number of Faults: " + fault + "\n");
        raspuns.append("\n");
    }

    void lru() {

        int frames, pointer = 0, hit = 0, fault = 0, ref_len, i;
        char ch;
        Boolean isFull = false;
        int buffer[];
        ArrayList<Integer> stack = new ArrayList<Integer>();
        int reference[];
        int mem_layout[][];
        String aux = pagini.pages1.getText().toString();
        ref_len1 = MainActivity.pages_no.getText().toString();
        frames1 = MainActivity.frames_no.getText().toString();
        ref_len = Integer.parseInt(ref_len1);
        frames = Integer.parseInt(frames1);

        reference = new int[ref_len];
        mem_layout = new int[ref_len][frames];
        buffer = new int[frames];
        for (int j = 0; j < frames; j++)
            buffer[j] = -1;

        for (i = 0; i < ref_len; i++) {
            ch = aux.charAt(i);
            reference[i] = ch - '0';
        }

        for (i = 0; i < ref_len; i++) {
            if (stack.contains(reference[i])) {
                stack.remove(stack.indexOf(reference[i]));
            }
            stack.add(reference[i]);
            int search = -1;
            for (int j = 0; j < frames; j++) {
                if (buffer[j] == reference[i]) {
                    search = j;
                    hit++;
                    break;
                }
            }
            if (search == -1) {
                if (isFull) {
                    int min_loc = ref_len;
                    for (int j = 0; j < frames; j++) {
                        if (stack.contains(buffer[j])) {
                            int temp = stack.indexOf(buffer[j]);
                            if (temp < min_loc) {
                                min_loc = temp;
                                pointer = j;
                            }
                        }
                    }
                }
                buffer[pointer] = reference[i];
                fault++;
                pointer++;
                if (pointer == frames) {
                    pointer = 0;
                    isFull = true;
                }
            }
            for (int j = 0; j < frames; j++)
                mem_layout[i][j] = buffer[j];
        }
        raspuns.append("LRU");
        raspuns.append("\n");
        for (i = 0; i < frames; i++) {
            for (int j = 0; j < ref_len; j++)
                raspuns.append(mem_layout[j][i] + " ");
            raspuns.append("\n");
        }

        raspuns.append("\n");
        raspuns.append("The number of Hits: " + hit);
        raspuns.append("\n");
        raspuns.append("Hit Ratio: " + (float) ((float) hit / ref_len));
        raspuns.append("\n");
        raspuns.append("The number of Faults: " + fault + "\n");
        raspuns.append("\n");
    }

    void optimal() {
        int frames, pointer = 0, hit = 0, fault = 0, ref_len, i;
        char ch;
        Boolean isFull = false;
        int buffer[];
        ArrayList<Integer> stack = new ArrayList<Integer>();
        int reference[];
        int mem_layout[][];
        String aux = pagini.pages1.getText().toString();
        ref_len1 = MainActivity.pages_no.getText().toString();
        frames1 = MainActivity.frames_no.getText().toString();
        ref_len = Integer.parseInt(ref_len1);
        frames = Integer.parseInt(frames1);

        reference = new int[ref_len];
        mem_layout = new int[ref_len][frames];
        buffer = new int[frames];
        for (int j = 0; j < frames; j++)
            buffer[j] = -1;

        for (i = 0; i < ref_len; i++) {
            ch = aux.charAt(i);
            reference[i] = ch - '0';
        }

        for (i = 0; i < ref_len; i++) {
            int search = -1;
            for (int j = 0; j < frames; j++) {
                if (buffer[j] == reference[i]) {
                    search = j;
                    hit++;
                    break;
                }
            }
            if (search == -1) {
                if (isFull) {
                    int index[] = new int[frames];
                    boolean index_flag[] = new boolean[frames];
                    for (int j = i + 1; j < ref_len; j++) {
                        for (int k = 0; k < frames; k++) {
                            if ((reference[j] == buffer[k]) && (index_flag[k] == false)) {
                                index[k] = j;
                                index_flag[k] = true;
                                break;
                            }
                        }
                    }
                    int max = index[0];
                    pointer = 0;
                    if (max == 0)
                        max = 200;
                    for (int j = 0; j < frames; j++) {
                        if (index[j] == 0)
                            index[j] = 200;
                        if (index[j] > max) {
                            max = index[j];
                            pointer = j;
                        }
                    }
                }
                buffer[pointer] = reference[i];
                fault++;
                if (!isFull) {
                    pointer++;
                    if (pointer == frames) {
                        pointer = 0;
                        isFull = true;
                    }
                }
            }
            for (int j = 0; j < frames; j++)
                mem_layout[i][j] = buffer[j];
        }
        raspuns.append("OptiamlReplacement");
        raspuns.append("\n");
        for (i = 0; i < frames; i++) {
            for (int j = 0; j < ref_len; j++)
                raspuns.append(mem_layout[j][i] + " ");
            raspuns.append("\n");
        }

        raspuns.append("\n");
        raspuns.append("The number of Hits: " + hit);
        raspuns.append("\n");
        raspuns.append("Hit Ratio: " + (float) ((float) hit / ref_len));
        raspuns.append("\n");
        raspuns.append("The number of Faults: " + fault + "\n");
        raspuns.append("\n");
    }

    void clock() {

        int frames, pointer = 0, hit = 0, fault = 0, ref_len, i;
        int buffer[][];
        int reference[];
        int mem_layout[][];
        int used_layout[][];
        char ch;

        String aux = pagini.pages1.getText().toString();
        ref_len1 = MainActivity.pages_no.getText().toString();
        frames1 = MainActivity.frames_no.getText().toString();
        ref_len = Integer.parseInt(ref_len1);
        frames = Integer.parseInt(frames1);

        reference = new int[ref_len];
        mem_layout = new int[ref_len][frames];
        used_layout = new int[ref_len][frames];
        buffer = new int[frames][2];

        for (int j = 0; j < frames; j++) {
            buffer[j][0] = -1;
            buffer[j][1] = 0;
        }
        for (i = 0; i < ref_len; i++) {
            ch = aux.charAt(i);
            reference[i] = ch - '0';
        }
        for (i = 0; i < ref_len; i++) {
            int search = -1;
            for (int j = 0; j < frames; j++) {
                if (buffer[j][0] == reference[i]) {
                    search = j;
                    hit++;
                    buffer[j][1] = 1;
                    break;
                }
            }
            if (search == -1) {

                while (buffer[pointer][1] == 1) {
                    buffer[pointer][1] = 0;
                    pointer++;
                    if (pointer == frames)
                        pointer = 0;
                }
                buffer[pointer][0] = reference[i];
                buffer[pointer][1] = 1;
                fault++;
                pointer++;
                if (pointer == frames)
                    pointer = 0;
            }
            for (int j = 0; j < frames; j++) {
                mem_layout[i][j] = buffer[j][0];
                used_layout[i][j] = buffer[j][1];
            }
        }
        raspuns.append("ClockReplacement");
        raspuns.append("\n");
        for (i = 0; i < frames; i++) {
            for (int j = 0; j < ref_len; j++) {
                raspuns.append(mem_layout[j][i] + " ");
                raspuns.append(used_layout[j][i] + "/");
            }
            raspuns.append("\n");
        }

        raspuns.append("\n");
        raspuns.append("The number of Hits: " + hit);
        raspuns.append("\n");
        raspuns.append("Hit Ratio: " + (float) ((float) hit / ref_len));
        raspuns.append("\n");
        raspuns.append("The number of Faults: " + fault + "\n");
        raspuns.append("\n");
    }

}

