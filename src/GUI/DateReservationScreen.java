package GUI;

import Main.NextScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateReservationScreen extends JFrame {
    private JLabel monthLabel; // 현재 월을 표시하는 라벨
    private JPanel calendarPanel; // 달력 패널
    private Calendar cal; // 달력 객체
    private String username; // 사용자 이름
    private Set<String> availableDates; // 선택 가능한 날짜들을 저장하는 Set

    // 생성자: 사용자 이름을 인자로 받아 초기화
    public DateReservationScreen(String username) {
        this.username = username;
        setTitle("영화별 예매"); // 창 제목 설정
        setSize(500, 300); // 창 크기 설정

        // 헤더 패널(상단 파란색 부분)
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(65, 105, 225)); // 배경색 설정
        headerPanel.setPreferredSize(new Dimension(300, 50)); // 패널 크기 설정
        add(headerPanel, BorderLayout.NORTH); // 패널을 창의 북쪽에 추가

        // 달력 패널
        JPanel mainPanel = new JPanel(new BorderLayout());
        monthLabel = new JLabel("", SwingConstants.CENTER); // 월 라벨 생성 및 가운데 정렬
        calendarPanel = new JPanel(new GridLayout(0, 7, 5, 5)); // 달력 패널 생성 및 그리드 레이아웃 설정

        mainPanel.add(monthLabel, BorderLayout.NORTH); // 메인 패널에 월 라벨 추가
        mainPanel.add(calendarPanel, BorderLayout.CENTER); // 메인 패널에 달력 패널 추가

        add(mainPanel, BorderLayout.CENTER); // 메인 패널을 창의 중앙에 추가

        // 메인으로 돌아가는 버튼
        JButton mainButton = new JButton("메인으로");
        mainButton.setBackground(new Color(65, 105, 255)); // 버튼 배경색 설정
        mainButton.setForeground(Color.BLACK); // 버튼 글자색 설정
        mainButton.addActionListener(e -> {
            dispose(); // 현재 창 닫기
            new NextScreen(username); // 메인 화면으로 돌아가기
        });
        add(mainButton, BorderLayout.SOUTH); // 버튼을 창의 남쪽에 추가

        cal = Calendar.getInstance(); // 현재 날짜로 달력 객체 초기화
        availableDates = getAvailableDates(); // 선택 가능한 날짜 초기화
        updateCalendar(); // 달력 업데이트

        // 프로그램 종료 설정 추가
        setLocationRelativeTo(null); // 화면 중앙에 창 표시
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 창을 닫을 때 이 화면만 종료
        setVisible(true); // 창을 보이도록 설정
    }

    // 선택 가능한 날짜들을 계산하여 반환하는 메서드
    private Set<String> getAvailableDates() {
        Set<String> dates = new HashSet<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 상영 정보 데이터 (시작일과 종료일)
        String[] screenData = {
                "2024-07-18", "2024-08-02",
                "2024-07-16", "2024-07-31",
                "2024-07-16", "2024-08-01",
                "2024-07-16", "2024-08-02"
        };

        // 각 상영 기간에 대해 모든 날짜를 계산하여 Set에 추가
        for (int i = 0; i < screenData.length; i += 2) {
            try {
                Date startDate = sdf.parse(screenData[i]);
                Date endDate = sdf.parse(screenData[i + 1]);
                Calendar tempCal = Calendar.getInstance();
                tempCal.setTime(startDate);

                while (!tempCal.getTime().after(endDate)) {
                    dates.add(sdf.format(tempCal.getTime()));
                    tempCal.add(Calendar.DAY_OF_MONTH, 1);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return dates;
    }

    // 달력을 업데이트하는 메서드
    private void updateCalendar() {
        calendarPanel.removeAll(); // 달력 패널 초기화

        int year = cal.get(Calendar.YEAR); // 현재 연도 가져오기
        int month = cal.get(Calendar.MONTH); // 현재 월 가져오기

        monthLabel.setText(year + "년 " + (month + 1) + "월"); // 월 라벨 업데이트

        // 요일 이름 추가
        String[] dayNames = {"일", "월", "화", "수", "목", "금", "토"};
        for (String dayName : dayNames) {
            JLabel label = new JLabel(dayName, SwingConstants.CENTER);
            calendarPanel.add(label);
        }

        // 달력의 시작 위치 계산
        cal.set(Calendar.DAY_OF_MONTH, 1);
        int startDay = cal.get(Calendar.DAY_OF_WEEK);
        int numberOfDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        // 달력의 시작 전 빈 공간 추가
        for (int i = 1; i < startDay; i++) {
            calendarPanel.add(new JLabel(""));
        }

        // 날짜 버튼 생성 및 추가
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 1; i <= numberOfDays; i++) {
            JButton dayButton = new JButton(String.valueOf(i));
            cal.set(Calendar.DAY_OF_MONTH, i);
            String currentDate = sdf.format(cal.getTime());

            // 선택 가능한 날짜인 경우 버튼 활성화
            if (availableDates.contains(currentDate)) {
                dayButton.setEnabled(true);
                dayButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose(); // 현재 DateReservationScreen 창 닫기
                        new MovieListScreen(username, currentDate); // MovieListScreen 열기
                    }
                });
            } else {
                dayButton.setEnabled(false); // 선택 불가능한 날짜는 버튼 비활성화
            }
            calendarPanel.add(dayButton);
        }

        calendarPanel.revalidate(); // 달력 패널 갱신
        calendarPanel.repaint(); // 달력 패널 다시 그리기
    }
}