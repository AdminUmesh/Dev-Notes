// Change first Char of String into UpperCase after _ found
public class Modifi_String {
    public static void main(String [] args){
        String name="my_name_is_umesh";
        StringBuilder sb= new StringBuilder();
        for(int i=0; i<name.length(); i++){
            if(name.charAt(i)=='_'){
                sb.append(name.charAt(i));
                i++;
                sb.append(Character.toUpperCase(name.charAt(i)));
            }else{
                sb.append(name.charAt(i));
            }
        }
        System.out.println(sb);
    }
}
