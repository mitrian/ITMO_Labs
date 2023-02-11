import re
file=open('Tuesday.xml','r', encoding="UTF-8")
masstr = re.findall(r'[^\t]*', file.read())
#print(masstr)
text=[]
tabs=[]
kt=0
yaml=''
for i in range(len(masstr)):
    if len(masstr[i])==0:
        kt+=1
    else:
        if masstr[i][1]!='/':
            text.append([masstr[i].replace('<','').replace('/>\n','').replace('>\n',''),kt])
        kt=0

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


file = open('TuesdayDop2.yaml', 'w')
file.write(yaml)
file.close()
