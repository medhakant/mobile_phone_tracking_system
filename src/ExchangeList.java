/*
author: Medha Kant
*/
public class ExchangeList {
    public static Exchange head;  // head of exchangeList , the pointer to first exchange

//This method inserts a Exchange "b" in the exchange list and makes "a" it's parent and in case there is no exchange "a" , it throws an exception
    public void Insert(ExchangeList.Exchange a,ExchangeList.Exchange b){
        if (IsMember(a)){
            ExchangeList.Exchange n = this.head;
            while (n.next!=null){
                n=n.next;
            }
            n.next = b;
            b.parent=a;

            return;
        }
    }

    public void Insert(ExchangeList.Exchange a){
            ExchangeList.Exchange n = this.head;
            if (n==null)
                head=a;
            else{
                while (n.next!=null){
                    n=n.next;
                }
                n.next = a;
            }
            return;
    }


//This method checks where the given exchange "a" is a part of the exchange list or not and returns a boolean value
    public Boolean IsMember(ExchangeList.Exchange a){
        ExchangeList.Exchange n=this.head;
        while (n!=null){
            if(n.uid==a.uid)
                return true;

            n=n.next;
        }

        return false;
    }


//This is the exchange class that has it's various properties defined 
//This is like the node of the exchangeList
    static class Exchange {
        public int uid; //the identifier of Exchange
        public Exchange parent; //defines the parent of the exchange
        public Exchange next;   //gives a pointer to the next exchange of the exchangeList
        public MobilePhoneSet residentPhoneSet;  //this is the resident phoneset of the exchange

//default constructor for the Exchange class
        public  Exchange(int number){
            this.uid =number;
            this.parent=null;
            this.next=null;
            this.residentPhoneSet=new MobilePhoneSet();
        }

//returns the parent of the exchange
        public Exchange parent(){
            return this.parent;
        }

//returns the no. of children of the exchange
        public int numChildren(){
            int i=0;
            ExchangeList.Exchange n = head;
            if(n!=null){
                while (n!=null){
                    if (n.parent==this)
                        i=i+1;
                    n=n.next;
                }
            }
            else{
                LinkedList.Node counter =residentPhoneSet.llist.head;
                while (counter!=null){
                    if (((MobilePhone)counter.data).location()==this)
                        i=i+1;
                    counter=counter.next;
                }
            }
            return i;

        }

        public boolean isChild(ExchangeList.Exchange x){
            if (this.numChildren()==0)
                return false;
            else if (this.numChildren()>0){
                for(int i=0;i<this.numChildren();i++){
                    if (this.child(i).uid==x.uid)
                        return true;
                }
            }

            return false;

        }


//returns the "i"th child of the exchange and in case the "i"th child doesn't exists ,it throws an exception        
        public Exchange child(int i){
            ExchangeList.Exchange n = head;
            while (n.uid !=this.uid){
                n=n.next;
            }
            if (this.uid==head.uid)
                n=n.next;
            while (n!=null && i>=0){
                if (n.parent().uid==this.uid){
                    i=i-1;
                }
                if (i>=0)
                    n =n.next;
            }
            return n;
        }

//checks whether the exchange is the root of the tree
        public Boolean isRoot(){
            if (this.parent==null)
                return true;
            else
                return false;
        }

//returns the subtree corresponding to it's "i"th child
        public RoutingMapTree subtree(int i){
            RoutingMapTree subTree = new RoutingMapTree();
            subTree.root = child(i);
            subTree.phoneSet = child(i).residentSet();
            return subTree;
        }
//returns the resident phoneset of the exchange
        public MobilePhoneSet residentSet(){
            return this.residentPhoneSet;
        }
    }

    public static void main(String[] args) throws Exception{
        ExchangeList a =new ExchangeList();
        a.head = new Exchange(0);
        a.Insert(head,new Exchange(1));
        a.Insert(head,new Exchange(2));
        a.Insert(head,new Exchange(3));
        System.out.println(head.numChildren());

    }


}
