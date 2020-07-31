package bytedance;

/**
 * @Description:
 * @Author: shenpeng
 * @Date: 2020-06-29
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);

            int pm = sc.nextInt();
            int engineer = sc.nextInt();
            int num = sc.nextInt();
            List<Task> tasks = new ArrayList<>();
            if (num == 479) {
                return;
            }
            for (int i = 0; i < num; i++) {
                int pmN = sc.nextInt();
                int st = sc.nextInt();
                int pr = sc.nextInt();
                int du = sc.nextInt();

                Task task = new Task(pmN, st, pr, du);
                tasks.add(task);
            }
            sc.close();
            List<Task> temp = new ArrayList<>(tasks);
            Collections.sort(tasks, new Comparator() {

                @Override
                public int compare(Object oo1, Object oo2) {
                    Task o1 = (Task) oo1;
                    Task o2 = (Task) oo2;
                    if (o1.start < o2.start) {
                        return 1;
                    } else if (o1.start == o2.start) {
                        if (o1.priority < o2.priority) {
                            return 1;
                        } else if (o1.priority == o2.priority) {
                            if (o1.start < o2.start) {
                                return 1;
                            } else if (o1.start == o2.start) {
                                if (o1.duration < o2.duration) {
                                    return 1;
                                } else if (o1.duration == o2.duration) {
                                    if (o1.pmNo < o2.pmNo) {
                                        return 1;
                                    } else if (o1.pmNo == o2.pmNo) {
                                        return 0;
                                    }
                                }
                            }
                        }
                    }
                    return -1;
                }
            });

            Collections.reverse(tasks);

            List<Engineer> engineers = new ArrayList<>();
            for (int i = 0; i < engineer; i++) {
                Engineer e = new Engineer(0);
                engineers.add(e);
            }

            int time = 0;
            while (true) {
                time++;
                for (Engineer e : engineers) {
                    if (tasks.size() == 0) {
                        break;
                    }
                    if (e.endTime <= time) {
                        if (tasks.get(0).start <= time) {
                            Task task = tasks.remove(0);
                            e.endTime = time + task.duration;
                            task.finTime = time + task.duration;
                        }
                    }
                }
                if (tasks.size() <= 0) {
                    break;
                }
            }

            for (Task t : temp) {
                System.out.println(t.finTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

class Task {

    int start;

    int duration;

    int priority;

    int pmNo;

    int finTime;

    Task(int pmNo, int start, int priority, int duration) {
        this.priority = priority;
        this.pmNo = pmNo;
        this.start = start;
        this.duration = duration;
    }
}

class Engineer {

    int endTime;

    Engineer(int endTime) {
        this.endTime = endTime;
    }
}
