import java.util.ArrayList;
import java.util.List;

//orignator
class Document{
    private String content;

    public Document(String content){
        this.content = content;
    }

    public void write(String text){
        this.content += text;
    }

    public String getContent(){
        return this.content;
    }

    public DocumentMemento createMemento(){
        return new DocumentMemento(this.content);
    }

    public void restore(DocumentMemento memento){
        this.content = memento.getSavedContent(); 
    }
}

//memento
class DocumentMemento{
    String content;
    DocumentMemento(String content){
        this.content = content;
    }

    public String getSavedContent(){
        return this.content;
    }
}

//care taker
class History {
    private List<DocumentMemento> mementos_list;

    public History(){
        this.mementos_list = new ArrayList<>();
    }

    public void addMemento(DocumentMemento memento){
        mementos_list.add(memento);
    }

    public DocumentMemento getMemento(int index){
        return this.mementos_list.get(index);
    }
}


public class MementoClient {
    public static void main(String[] args) {
       Document doc1 = new Document("This is Nij ");
       History history = new History();
       
       history.addMemento(doc1.createMemento());
       
       doc1.write("I am currently working ");
       
       history.addMemento(doc1.createMemento());

       doc1.write("at EI");

        System.out.println("Latest : " + doc1.getContent());

       doc1.restore(history.getMemento(0));

       System.out.println("After restore 1 : " + doc1.getContent());
       
       doc1.restore(history.getMemento(1));

       System.out.println("After restore 2 : " + doc1.getContent());
    }
}
