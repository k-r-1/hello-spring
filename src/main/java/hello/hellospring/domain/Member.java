package hello.hellospring.domain;

public class Member {

    private long id; // id 식별자 (데이터를 구분하기 위해 시스템이 저장하는 id)
    private String name; // 이름

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
