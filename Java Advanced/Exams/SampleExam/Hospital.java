package Exams.SampleExam;

import java.util.*;

public class Hospital {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedHashMap<String, LinkedHashMap<Integer, List<String>>> departmentsRoomsPatientsMap = new LinkedHashMap<>();
        LinkedHashMap<String, List<String>> doctorsPatientsMap = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> roomIDs = new LinkedHashMap<>();

        String input = sc.nextLine();
        while (!"Output".equals(input)) {
            String currentDepartment = input.substring(0, input.indexOf(" "));
            String doctor = input.substring(input.indexOf(" ") + 1, input.lastIndexOf(" "));
            String patient = input.substring(input.lastIndexOf(" ") + 1);

            doctorsPatientsMap.putIfAbsent(doctor, new ArrayList<>());
            doctorsPatientsMap.get(doctor).add(patient);

            if (!departmentsRoomsPatientsMap.containsKey(currentDepartment)) {
                departmentsRoomsPatientsMap.put(currentDepartment, new LinkedHashMap<>());
                roomIDs.putIfAbsent(currentDepartment, 0);
                roomIDs.put(currentDepartment, roomIDs.get(currentDepartment) + 1);
                departmentsRoomsPatientsMap.get(currentDepartment).put(roomIDs.get(currentDepartment), new ArrayList<>());
                departmentsRoomsPatientsMap.get(currentDepartment).get(roomIDs.get(currentDepartment)).add(patient);

            } else {
                if (departmentsRoomsPatientsMap.get(currentDepartment).get(roomIDs.get(currentDepartment)).size() == 3) {
                    if (roomIDs.get(currentDepartment) < 20) {
                        roomIDs.put(currentDepartment, roomIDs.get(currentDepartment) + 1);
                        departmentsRoomsPatientsMap.get(currentDepartment).put(roomIDs.get(currentDepartment), new ArrayList<>());
                        departmentsRoomsPatientsMap.get(currentDepartment).get(roomIDs.get(currentDepartment)).add(patient);
                    }
                } else {
                    departmentsRoomsPatientsMap.get(currentDepartment).get(roomIDs.get(currentDepartment)).add(patient);
                }
            }


            input = sc.nextLine();
        }

        String newCommand = sc.nextLine();
        while(!"End".equals(newCommand)) {
            String[] data = newCommand.split("\\s+");
            if(departmentsRoomsPatientsMap.containsKey(data[0])) {
                if(data.length > 1) {
                    for (Map.Entry<Integer, List<String>> rooms : departmentsRoomsPatientsMap.get(data[0]).entrySet()) {
                        if(Integer.parseInt(data[1]) == rooms.getKey()) {
                            rooms.getValue().sort(String::compareTo);
                            for (String s : rooms.getValue()) {
                                System.out.println(s);
                            }
                            break;
                        }
                    }
                } else {
                    for (Map.Entry<String, LinkedHashMap<Integer, List<String>>> departmentLoop : departmentsRoomsPatientsMap.entrySet()) {
                        if (departmentLoop.getKey().equals(data[0])) {
                            for (Map.Entry<Integer, List<String>> room : departmentLoop.getValue().entrySet()) {
                                for (String s : room.getValue()) {
                                    if (s != null) {
                                        System.out.println(s);
                                    }
                                }
                            }
                        }
                    }
                }
            } else if(doctorsPatientsMap.containsKey(newCommand)) {
                doctorsPatientsMap.get(newCommand).sort(String::compareTo);
                for (String s : doctorsPatientsMap.get(newCommand)) {
                    System.out.println(s);
                }
            }
            newCommand = sc.nextLine();

        }

    }
}

