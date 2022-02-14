/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;
import Data.*;
import java.io.BufferedReader;
import tools.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.StringTokenizer;
/**
 *
 * @author PC
 */
public class Doctor {
    private ArrayList<Injection> injecList = new ArrayList<Injection>();
    private ArrayList<Student> studentList = new ArrayList<Student>();
    private ArrayList<Vaccine> vacList = new ArrayList<Vaccine>();
    //ArrayList<matchedVacine> matchVac = new ArrayList<matchedVacine>();
    private String injecFile = "Injection.dat", stuFile = "Student.dat", vacFile="Vacine.dat", matchVacFile = "matchVaccine.dat";
    private Scanner sc =new Scanner(System.in);
    private LocalDate today = java.time.LocalDate.now(); 
    //Hiển thị tất cả các injection
    public void showAllInjection(){
        if(injecList.isEmpty()){
            System.out.println("The infomation of injection is empty!");
            return;
        }
        System.out.println(Injection.tabbleInjec);
        for(int i = 0; i < injecList.size(); i++){
            System.out.println(injecList.get(i));   
        }
    }
    //Thêm thông tin mũi 1
    public void addInjectionInfor(){
        boolean dup = true; 
        String injectCode = null;
        //Nhập inject code mới, kiểm tra không cho trùng và đúng kiểu IJ + 3 dit
        while(dup){
            injectCode = getString.getInjecCode();
            dup = false;
            for(int i = 0; i < injecList.size(); i++){
                if(injectCode.equals(injecList.get(i).getCode())){
                   dup = true;
                }
            }
            if(dup) System.out.println("The code " + injectCode + " is duplicated, please enter again!");
        }
        //Nhập vào mã số sinh viên và kiểm tra xem có tồn tại trong student list không, hay là có tồn tại trong injection list không.
        
        String stuCode = null;
        //System.out.println(studentList.size());
        while(!dup){
            printStudentList();
            int flag = 0;
            System.out.println("Please enter the code of the student: ");
            stuCode = getString.inputStudentCode();
            //Kiểm tra xem code sinh viên này có tồn tại trong student list hay không
            for(int i = 0; i < studentList.size(); i++){
                if(stuCode.equals(studentList.get(i).getCode())){
                    dup = true;
                    flag = 1;
                    break;
                }
            }
            //Nếu không in ra câu lệnh không tồn tại, và phải nhập lại, không chạy câu lệnh sau
            if(!dup){
                System.out.println("The student with code " + stuCode + " is not exist, please enter again!!");
            }
            if(flag == 1){
                //Kiểm tra mã số sinh viên này có tồn tại trong injectList hay không
                for(int i = 0; i < injecList.size();i++){
                    if(stuCode.equals(injecList.get(i).getStudent().getCode())){
                        dup = false;
                        break;
                    }
                }
                //Nếu có in ra câu lệnh mã số sinh viên này đã tồn tại và nhập lại.   
                if(!dup){
                    System.out.println("The code " + stuCode + " is exist in the injection list, please enter again!");
                }
            }
        }
        Student student = returnStudentWithStudentCode(stuCode);
        //Nhập nơi tiêm
        String place = getString.getPlace("Please enter the place: ");
        //Nhập ngày tiêm
        Date date = null;
        boolean checkDate = false;
        while(!checkDate){
            date = Date.addDate();
            checkDate = checkDateOfInjecOne(date);
            if(!checkDate){
                System.out.println("Please enter again!");
            }
        }
        //Nhập loại vaccine
        Vaccine vac = inputVaccine();
        EachVac eachVac[] = new EachVac[2];
        eachVac[0] = new EachVac(place, date, vac);
        injecList.add(new Injection(injectCode, eachVac, student));
        saveData(injecFile, injecList);
        System.out.println("Add success!!!");
    }
    //Cập nhật thông tin injection ( thêm mũi 2)
    public void updateInjection(){
        if(injecList.isEmpty()){
            System.out.println("Nothing to update!");
            return;
        }
        String injecCode = null;
        boolean valid = false;
        while(!valid){
            showAllInjection();
            System.out.print("Please enter the injection code to update: ");  
            injecCode = sc.nextLine().toUpperCase().trim();
            valid = isExist(injecCode);
            if(!valid) System.out.println("The code " + injecCode + " is not exist, please try again!");
            
            else if(valid){
                Injection checkInjec = returnInjectionWithInjectionCode(injecCode);
                if(checkInjec.getVac(1).getPlace() != null){
                    valid = false;
                    System.out.println("Student " + checkInjec.getStudent().getName() + " is completed 2 injections!");
                    int choice = getInt.get(1, 2, "Do you want to try with another code ?\n1.Yes\n2.No\nEnter your choice: ");
                    if(choice == 2) return;
                }            
            }
        }
        Injection thisInjection = returnInjectionWithInjectionCode(injecCode);
        String place = getString.getPlace("Please enter the place: ");
        Date date = inputDateInjecTwo(thisInjection.getVac(0).getDate());
        EachVac vacTwo = new EachVac(place, date, thisInjection.getVac(0).getVac());
        thisInjection.setVac(vacTwo, 1);
        saveData(injecFile, injecList);
        System.out.println("Update success");
    }
    //Tìm kiếm injection bằng student code
    public void searchByStudentCode(){
        int toContinue = 1;
        while(toContinue == 1){
            boolean dup = false;
            String stuCode = null;
            while(!dup){
                stuCode = getString.inputStudentCode();
                dup = checkStudentCode(stuCode);
                if(!dup){
                    int choice = getInt.get(1, 2, "Student with code " + stuCode + " is not exist in injection list!\nDo you want to try again?\n1.Yes\n2.No\nPlease enter your choice: ");
                    if(choice == 2) return;
                }
            }
            Injection injectionInfo = returnInjectionByStudentCode(stuCode);
            System.out.println(Injection.tabbleInjec);
            System.out.println(injectionInfo);    
            toContinue = getInt.get(1, 2, "Do you want to search another injection ?\n1.Yes\n2.No\nEnter your choice: ");
        }
    }
    //Xoá thông tin injection bằng injection code
    public void deleteInjection(){
        boolean dup = false;
        String injecCode = null;
        while(!dup){
            injecCode = getString.getInjecCode();
            dup = isExist(injecCode);
            if(!dup){
                int choice = getInt.get(1, 2, "Injection with code " + injecCode + " is not exist in injection list!\nDo you want to try again?\n1.Yes\n2.No\nPlease enter your choice: ");
                if(choice == 2) return;
            }
        }
        Injection thisInjection = returnInjectionWithInjectionCode(injecCode);
        int confirm = getInt.get(1, 2, "The injection with code " + injecCode + " found!\nDo you want to remove the injection of student " + thisInjection.getStudent().getName() + " ?\n1.Yes\n2.No\nEnter your choice: ");
        if(confirm == 1) { 
            injecList.remove(thisInjection);
            System.out.println("Delete succes");
        }
        else System.out.println("The injection with code " + injecCode + " of student " + thisInjection.getStudent().getName() + " is safely\nBack to menu.");
    
        saveData(injecFile, injecList);
    }
    //Truyền vào một ngày, yêu cầu người dùng nhập vào 1 ngày sau ngày đó từ 4 đến 12 tuần 
    //4 - 12 tuần phải sau từ 28 đến 84 ngày
    private Date inputDateInjecTwo(Date dateInjecOne){
        Date today = new  Date(this.today.getDayOfMonth(), this.today.getMonthValue(), this.today.getYear());
        boolean valid = false;
        Date dateInjecTwo = null;
        while(!valid){
            boolean flag = true;
            dateInjecTwo = Date.addDate();
            if(Date.countDate(dateInjecTwo) > Date.countDate(today)){
                System.out.println("The date you just entered has passed today!! \nPlease enter again!");
                flag = false;
            } 
            if(flag){
                if((Date.countDate(dateInjecTwo) - Date.countDate(dateInjecOne)) < 28 ){
                    //System.out.println("Ij one = " + Date.countDate(dateInjecOne) +"  "  + dateInjecOne);
                    //System.out.println("Ij two = " + Date.countDate(dateInjecTwo) +"  "  + dateInjecTwo);
                    System.out.println("The day of the 2nd injection must be at least two weeks after the 1st injection!!!\nPlease enter again!");
                    flag = false;
                }
                else if((Date.countDate(dateInjecTwo) - Date.countDate(dateInjecOne)) > 84){
                    System.out.println("The second injection should not be more than 8 weeks after the first injection!!!\nPlease enter again!");
                    flag = false;
                }
            }
            if(flag) valid = true;
        }
        return dateInjecTwo;
    }
    //Hàm yêu cầu người dùng nhập loại vaccine, trả về biến vaccine
    private Vaccine inputVaccine(){
        String code;
        boolean dup = false;
        int index = 0;
        while (!dup){
            System.out.println(Vaccine.tabbleVac);
            for(int i = 0; i < vacList.size(); i++){
                System.out.println(vacList.get(i));
            }
            System.out.print("Enter the code of Vaccine: ");
            code = sc.nextLine().toUpperCase().trim();
            for(int i = 0; i < vacList.size(); i++){
                if(code.equals(vacList.get(i).getCode())){
                    index = i;
                    dup = true;
                    break;
                }
            }           
            if(!dup) System.out.println("The code is not exist!!! Please enter again!");
        }
        return vacList.get(index);
    }
    //Hàm đưa vào injec code và trả về biến inject đó, nếu k thấy trả về null    
    private Injection returnInjectionWithInjectionCode(String injectCode){
        for(int i = 0; i < injecList.size(); i++){
            if(injecList.get(i).getCode().equals(injectCode)){
                return injecList.get(i);
            }
        }
        return null;
    }
    //Hàm đưa vào injec code và kiểm tra xem inject code đó có tồn tại hay k, true nếu có và false nếu k
    private boolean isExist(String injectCode){
        for(int i =0; i < injecList.size(); i++){
            if(injecList.get(i).getCode().equals(injectCode)){
                return true;
            }
        }
        return false;
    }
    
    //Truyền vào date, nếu trước 8/3/2021 thì false, nếu sau today thì false, còn lại true
    private boolean checkDateOfInjecOne(Date date){
        Date firstDayInjec = new Date(8, 3, 2021);
        if(Date.countDate(date) < Date.countDate(firstDayInjec)){
            System.out.println("The day is before " + firstDayInjec + " it must be after " + firstDayInjec + " !!!!");
            return false;
        }
        Date isToday = new Date(today.getDayOfMonth(), today.getMonthValue(), today.getYear());
        if(Date.countDate(date) > Date.countDate(isToday)){
            System.out.println("It must be before or today !!");
            return false;
        }
        return true;
    }
    //Truyền vào mssv của 1 sinh viên trả lại object sinh viên đó TRONG DANH SÁCH SINH VIÊN
    private Student returnStudentWithStudentCode(String stuCode){
        int index = 0;
        for(int i = 0; i < studentList.size(); i++){
            if(stuCode.equals(studentList.get(i).getCode())){
                index = i;
                break;
            }
        }
        return studentList.get(index);
    }
    //Truyền vào mssv check xem trong injec list có sinh viên đó hay không true nếu có và false nếu không
    private boolean checkStudentCode(String stuCode){
        for(int i = 0; i < injecList.size(); i++){
            if(injecList.get(i).getStudent().getCode().equals(stuCode)){
                return true;
            }
        }
        return false;
    }
    //Truyền vào mssv và trả về biến inject của sinh viên đó trong danh sách Injection list, nếu không có thì trả về null
    private Injection returnInjectionByStudentCode(String stuCode){
        for(int i = 0; i < injecList.size(); i++){
            if(injecList.get(i).getStudent().getCode().equals(stuCode)) return injecList.get(i);
        }
        return null;
    }
    //Hàm đưa vào vac code và kiểm tra xem inject code đó có tồn tại hay k, true nếu có và false nếu k
    private boolean isExistVacCode(String vacCode){
        for(int i =0; i < injecList.size(); i++){
            if(injecList.get(i).getVac(0).getVac().getCode().equals(vacCode)){
                return true;
            }
        }
        return false;
    }
    
    //Xóa tất cả những injec có tiêm loại vaccine này
    public void deleteInjectionByVacID(){
        boolean dup = false;
        Vaccine Vac = null;
        while(!dup){
            Vac = inputVaccine();
            dup = isExistVacCode(Vac.getCode());
            if(!dup){
                int choice = getInt.get(1, 2, "Injection with code " + Vac.getCode() + " is not exist in injection list!\nDo you want to try again?\n1.Yes\n2.No\nPlease enter your choice: ");
                if(choice == 2) return;
            }
        }
        int confirm = getInt.get(1, 2, "Do you want to remove all of the injection with vaccine " + Vac.getName() + " ?\n1.Yes\n2.No\nEnter your choice: ");
        if(confirm == 2) { 
            System.out.println("Back to the menu");
            return;
        }
        
        dup = isExistVacCode(Vac.getCode());
        for(int i = 0; i < injecList.size();i++){
            if(injecList.get(i).getVac(0).getVac().getCode().equals(Vac.getCode())){
                injecList.remove(injecList.get(i));
                i--;
            }    
        }        

        System.out.println("Delete succes.");
        saveData(injecFile, injecList);
    }

    private void printStudentList(){
        System.out.println(Student.tabbleStu);
        for(int i = 0; i < studentList.size(); i++){
            System.out.println(studentList.get(i));
        }
    }
    //Thêm student vào student list.
    public void addStudent(){
        studentList.add( new Student("SE150003", "PHAM NGOC MAI", "0918175544"));
        studentList.add( new Student("SE140001", "NGUYEN VAN AN", "0327856395"));
        studentList.add( new Student("SE140002", "TRAN THANH BINH", "0985632196"));
        studentList.add( new Student("SE150004", "NGUYEN HUU CUONG", "0164352845"));
        studentList.add( new Student("SE160005", "TRUONG TAN PHAT", "0845284932"));
        saveData(stuFile, studentList);
    }
    //Khởi tạo injec có sẵn vào inject List
    public void addInjectDeBai(){
        EachVac vacIJ001[] = new EachVac[2];
        vacIJ001[0] = new EachVac("SOC TRANG", new Date(23, 12, 2021),new Vaccine("4", "AstraZeneca"));
        vacIJ001[1] = new EachVac("HO CHI MINH", new Date(23, 1, 2022),new Vaccine("4", "AstraZeneca"));
        injecList.add(new Injection("IJ001", vacIJ001, new Student("SE150003", "PHAM NGOC MAI", "0918175544")));
        
        EachVac vacIJ002[] = new EachVac[2];
        vacIJ002[0] = new EachVac("SOC TRANG", new Date(23, 12, 2021),new Vaccine("10", "Pfizer"));
        injecList.add(new Injection("IJ002", vacIJ002, new Student("SE140001", "NGUYEN VAN AN", "0327856395")));
        
        EachVac vacIJ003[] = new EachVac[2];
        vacIJ003[0] = new EachVac("SOC TRANG", new Date(23, 12, 2021),new Vaccine("7", "Janssen"));
        injecList.add(new Injection("IJ003", vacIJ003, new Student("SE140002", "TRAN THANH BINH", "0985632196")));
        
        EachVac vacIJ004[] = new EachVac[2];
        vacIJ004[0] =  new EachVac("SOC TRANG", new Date(23, 12, 2021),new Vaccine("10", "Pfizer"));
        injecList.add(new Injection("IJ004", vacIJ004, new Student("SE150004", "NGUYEN HUU CUONG", "0164352845")));
        saveData(injecFile, injecList);
        
    }
    //Khởi tạo vaccine list
    public void addVaccine(){
        vacList.add(new Vaccine("2", "Sinopharm"));
        vacList.add(new Vaccine("4", "AstraZeneca"));
        vacList.add(new Vaccine("6", "Gam-COVID-Vac"));
        vacList.add(new Vaccine("7", "Janssen"));
        vacList.add(new Vaccine("9", "Moderna"));
        vacList.add(new Vaccine("10", "Pfizer"));
        
        saveData(vacFile, vacList);
    }
    //========================================I/O===========================================
    public void loadAllFile(){
        loadInjec(injecList);
        //loadStudent(studentList);
        //loadVac(vacList);
    }
    public void loadVacAndStu(){
        loadStudentTxt();
        loadVaccineTxt();
    }
    public void saveAllFile(){
        //saveData(stuFile, studentList);
        saveData(injecFile, injecList);
        //saveData(vacFile, vacList);
    }
    
    private void loadInjec(ArrayList obj){
        if(obj.size() > 0) obj.clear();
        try {
            File f = new File(injecFile);
            if (!f.exists()) return;
            FileInputStream fi = new FileInputStream(f);
            ObjectInputStream fo = new ObjectInputStream(fi);
            Injection b;
            while ((b=(Injection)(fo.readObject())) != null){
                obj.add(b);
            }
            fo.close();fi.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    private void loadStudent(ArrayList obj){
        if(obj.size() > 0) obj.clear();
        try {
            File f = new File(stuFile);
            if (!f.exists()) return;
            FileInputStream fi = new FileInputStream(f);
            ObjectInputStream fo = new ObjectInputStream(fi);
            Student b;
            while ((b=(Student)(fo.readObject())) != null){
                obj.add(b);
            }
            fo.close();fi.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    private void loadVac(ArrayList obj){
        if(obj.size() > 0) obj.clear();
        try {
            File f = new File(vacFile);
            if (!f.exists()) return;
            FileInputStream fi = new FileInputStream(f);
            ObjectInputStream fo = new ObjectInputStream(fi);
            Vaccine b;
            while ((b=(Vaccine)(fo.readObject())) != null){
                obj.add(b);
            }
            fo.close();fi.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    private void saveData(String fName, ArrayList obj){
        try {
        if(obj.size() == 0){
            FileOutputStream f = new FileOutputStream(fName);
            ObjectOutputStream fo = new ObjectOutputStream(f);
            fo.writeObject(null);
            return;
        }
        
            FileOutputStream f = new FileOutputStream(fName);
            ObjectOutputStream fo = new ObjectOutputStream(f);
            for(int i = 0; i < obj.size(); i++){
                fo.writeObject(obj.get(i));
            }
            fo.close();f.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    //Load 2 file từ đề
    private void loadVaccineTxt(){
        try{
            File f = new File("vaccine.txt");
            if(!f.exists()) return;
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            String details;
            while ((details = bf.readLine()) != null ){
                StringTokenizer stk = new StringTokenizer(details, ";");
                String code = stk.nextToken().toUpperCase();
                String name = stk.nextToken().toUpperCase();
                //create vac
                Vaccine vac = new Vaccine(code, name);
                vacList.add(vac);
            }
            bf.close();fr.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    private void loadStudentTxt(){
        try{
            File f = new File("student.txt");
            if(!f.exists()) return;
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            String details;
            while ((details = bf.readLine()) != null ){
                
                StringTokenizer stk = new StringTokenizer(details, ";");
                String code = stk.nextToken().toUpperCase();
                String name = stk.nextToken().toUpperCase();
                String phone = stk.nextToken().toUpperCase();
                //create vac
                Student std = new Student(code, name, phone);
                studentList.add(std);
            }
            bf.close();fr.close();
        }
        catch(Exception e){
            System.out.println(e);
        }        
    }
}
