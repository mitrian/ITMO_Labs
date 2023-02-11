#<?xml version="1.0" encoding="UTF-8" ?>
file = open('Tuesday.xml', 'r', encoding="utf-8")
iznachtext=file.readlines()
file.close()
text1=[]
text=[]
for j in iznachtext:
    nov=j.split('<')
    text1.append([nov[1].replace('/>\n','').replace('>\n',''),nov[0].count('\t')])
for i in text1:
    if i[0][0]!='/':
        text.append(i)

yaml=''
predtab=0

for i in text:
    t=i[0]
    index=i[1]+1


    if i[1]==0 and t[0]!='/':
        yaml=yaml+' '*4*i[1]+t+':'+'\n'
    else:
        tekstr=t.split(' ')

        yaml=yaml+' '*4*i[1]+tekstr[0]+':'+'\n'


        if tekstr[0]=='lesson':
            if tekstr[1].count('"')==1:
                yaml=yaml+' '*4*(i[1]+1)+tekstr[1].split('=')[0]+': '+tekstr[1].split('"')[-1]+' '
                for j in tekstr[2:]:
                    yaml=yaml+j.replace('"','')+' '
            else:
                yaml=yaml+' '*4*(i[1]+1)+tekstr[1].split('=')[0]+': '+tekstr[1].split('"')[1]+' '
            yaml=yaml+'\n'


        if tekstr[0]=='day' or tekstr[0]=='time':
            yaml=yaml+' '*4*(i[1]+1)+tekstr[1].split('=')[0]+': '+tekstr[1].split('"')[1]+'\n'


        if tekstr[0]=='place':
            if 'building' in tekstr[2]:
                yaml=yaml+' '*4*index+tekstr[1].split('=')[0]+': '+tekstr[1].split('"')[1]+'\n'
                yaml=yaml+' '*4*index+tekstr[2].split('=')[0]+': '+tekstr[2].split('"')[1]+' '
                for j in tekstr[3:]:
                    yaml=yaml+j.replace('"','')+' '
                yaml+='\n'
            else:
                yaml=yaml+' '*4*index+tekstr[1].split('=')[0]+': '+tekstr[1].split('"')[1]+' '
                tekind=1
                for j in tekstr[2:]:
                    tekind+=1
                    if 'building' in j:
                        break
                    else:
                        yaml=yaml+j.replace('"','')+' '
                yaml=yaml+'\n'+' '*4*index+tekstr[tekind].split('=')[0]+': '+tekstr[tekind].split('"')[1]+' '
                for j in tekstr[tekind+1:]:
                    yaml=yaml+j.replace('"','')+' '
                yaml+='\n'



        if tekstr[0]=='teacher':
            yaml=yaml+' '*4*(i[1]+1)+tekstr[1].split('=')[0]+': '+tekstr[1].split('"')[1]+' '
            for j in tekstr[2:]:
                yaml=yaml+j.replace('"','')+' '
            yaml+='\n'


        if tekstr[0]=='format':
            yaml=yaml+' '*(i[1]+1)*4+tekstr[1].split('=')[0]+': '+tekstr[1].split('"')[-1]+' '
            for j in tekstr[2:]:
                yaml=yaml+j.replace('"','')+' '
            yaml=yaml+'\n'


    predtab=i[1]


file = open('TuesdayMain.yaml', 'w')
file.write(yaml)
file.close()
