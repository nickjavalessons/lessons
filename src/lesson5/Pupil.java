package lesson5;

public class Pupil {
    int id;
    ExamResult[] examResults;
    public Pupil(int id){
        this.id = id;
    }
    public void setExams(String[] ex, int[] marks) throws IllegalArgumentException{
        if(ex.length != marks.length) throw new IllegalArgumentException("Длины массивов не равны");
        examResults = new ExamResult[ex.length];
        for (int i = 0; i < ex.length; i++) {
            ExamResult er = new ExamResult(ex[i], marks[i]);
            examResults[i] = er;
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Студент " + id).append(System.lineSeparator());
        if(examResults == null) return "";
        for(ExamResult er: examResults){
            sb.append(er).append(System.lineSeparator());
        }
        return sb.toString();
    }
    private class ExamResult {
        String subj;
        int marks;
        public ExamResult(String subj, int marks) {
            this.subj = subj;
            this.marks = marks;
        }
        @Override
        public String toString() {
            return subj + ": " + ((marks > 2)?"Сдал":"Не сдал") ;
        }
    }

    public static void main(String[] args) {
        Pupil pupil = new Pupil(1324);
        String ex[] = {"Математика","Биология"};
        int marks[] = { 2, 5 };
        pupil.setExams(ex, marks);
        System.out.println(pupil);
    }
}
