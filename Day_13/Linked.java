class Node{
    int data;
    Node next;
    Node(int data){
        this.data=data;
        this.next=null;
    }
}

class SinglylinkedList{
    Node head=null;
    void insertatstart(int data){
        Node newnode= new Node(data);
        Node ptr=head;
        if(head==null){
            head=newnode;
        }
        else{
            newnode.next=ptr;
            head=newnode;
        }
    }
    void insertend(int data){
        Node newnode=new Node(data);
        Node ptr=head;
        if(head==null){
            head=newnode;
        }
        else{
            while(ptr.next!=null){
                ptr=ptr.next;
            }
            ptr.next=newnode;
        }
    }
    void insertmid(int data,int index){
        Node newnode=new Node(data);
        Node ptr=head;
        if(index==0){
            insertatstart(data);
        }
        else{
            for(int i=0;i<index-1;i++){
                ptr=ptr.next;
            }
            newnode.next=ptr.next;
            ptr.next=newnode;
        }    
    }
    void deleteatfirst(){
        Node ptr=head;
        if(head!=null){
            ptr=ptr.next;
            head=ptr;
        }
    }
    void display(){
        Node ptr=head;
        while(ptr!=null){
            System.out.println(ptr.data);
            ptr=ptr.next;
        }
    }
}

public class Linked{
    public static void main(String[] args){
        SinglylinkedList s=new SinglylinkedList();
        s.insertend(1);
        s.insertend(9);
        s.insertend(5);
        s.insertend(6);
        s.insertend(3);
        s.insertmid(7,0);
        s.insertmid(8,5);
        //s.deleteatfirst();
        s.display();

    }
}