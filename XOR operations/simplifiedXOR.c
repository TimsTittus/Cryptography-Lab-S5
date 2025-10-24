#include<stdio.h>
#include<string.h>

int main() {
    char str[20] = "Hello World";
    int key = 127;
    int len = strlen(str);
    char encoded[len+1];

    for(int i=0;i<=len;i++){
        encoded[i] = str[i]^key;
    }
    encoded[len]='\0';

    printf("Original: %s\n", str);
    printf("Encoded: %s\n", encoded);

    return 0;
}