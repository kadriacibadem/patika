public class MyList<T> {
    T[] array;
    T[] tempArray;
    int capacity=10;
    public MyList(){
        array= (T[]) new Object[10];
    }

    public MyList(int capacity){
        this.capacity = capacity;
        array = (T[]) new Object[capacity];
    }

    public int size(){
        int size = 0;
        for(T t : array){
            if(t != null){
                size++;
            }
        }
        return size;
    }
    public int getCapacity(){
        return this.capacity;
    }

    public void add(T data){
        if(this.size() == this.getCapacity()){
            int i = 0;
            this.capacity = capacity*2;
            tempArray = (T[]) new Object[capacity];
            for (T t : array){
                tempArray[i] = t;
                i++;
            }
            tempArray[this.size()] = data;
            this.array = tempArray;
        }else{
            array[this.size()] = data;
        }
    }

    @Override
    public String toString(){
        String str = "[";
        for(int i = 0;i<this.size();i++){
            if(this.array[i]!=null){
                str = str + this.array[i] + ",";
            }
        }
        if(!isEmpty()){
            str = str.substring(0, str.length() - 1);
        }
        return str= str+"]";
    }

    public T get(int index){
        if(index>=this.size()){
            return null;
        }else{
            return array[index];
        }
    }

    public T remove(int index){
        T removed = null;
        if(index>=this.size()){
            return null;
        }else{
            tempArray = (T[]) new Object[this.size()];
            for(int i=0;i<this.size();i++){
                if(i!=index){
                    tempArray[i] = array[i];
                }else{
                    removed = array[i];
                    for(int j=index;j<this.size();j++){
                        tempArray[j] = array[j+1];
                    }
                    break;
                }
            }
            this.array = tempArray;
        }
        return removed;
    }
    public T set(int index,T data){
        if(index>=this.size()){
            return null;
        }else{
            return array[index] = data;
        }
    }

    public int indexOf(T data){
        int index=-1;
        for(int i = 0 ; i<this.size();i++){
            if(array[i] == data){
                index = i;
                break;
            }
        }
        return index;
    }

    public int lastIndexOf(T data){
        int index=-1;
        for(int i = 0 ; i<this.size();i++){
            if(array[i] == data){
                index = i;
            }
        }
        return index;
    }
    public boolean isEmpty(){
        if(this.size()==0){
            return true;
        }else{
            return false;
        }
    }

    public T[] toArray(){
        return array;
    }

    public void clear(){
        array = (T[]) new Object[10];
    }

    public boolean contains(T data){
        for(int i = 0 ; i<this.size();i++){
            if(array[i] == data){
                return true;
            }
        }
        return false;
    }

    MyList<T> sublist(int start,int finish){
        MyList<T> subList = new MyList<T>();
        for(int i = start;i<=finish;i++){
            subList.add(array[i]);
        }
        return subList;
    }
}
