public class User {
    private int id;
    private String name;
    private int age;
    private String cpf;
    private String email;
    private String cel;
    private String cep;
    private String password;

    //Constructor
    public User(int id,String name,int age, String cpf, String email, String cel, String cep, String password){
        this.id=id;
        this.name=name;
        this.age=age;
        this.cpf=cpf;
        this.email=email;
        this.cel=cel;
        this.cep=cep;
        this.password=password;
    }

    //Gets and Sets

    //Necessidade de set ou get para password ?
    public void SetPass(String password){
        this.password=password;
    }
    public String getPass(){
        return password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCel() {
        return cel;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    //To array
    public String[] toArray() {
        return new String[] { String.valueOf(id),name, String.valueOf(age), cpf, email, cel, cep, password };
    }
}