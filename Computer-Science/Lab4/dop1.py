import xmltodict

with open('Tuesday.xml', encoding="utf-8") as xml_data:
    xml_parsed = xmltodict.parse(xml_data.read())
def pars(depth, list, yaml):
    for j in list:
        if len(str(j)) != len(j):
            for i in j:
                if i[0] == '@':
                    yaml += ('\n'+'  '*depth+str(i[1:])+':')
                else:
                    yaml += ('\n'+'  '*depth+str(i)+':')
                yaml = pars(depth+1, j[i], yaml)
            continue
        try:
            list[j]
        except TypeError:
            yaml += ('  '+str(list))
            break

        if j[0] == '@':
            yaml += ('\n'+'  '*depth+str(j[1:])+':')
        else:
            yaml += ('\n'+'  '*depth+str(j)+':')
        yaml = pars(depth+1, list[j], yaml)
    return yaml
yaml = pars(0, xml_parsed, '')


file = open('Tuesday1.yaml', 'w')
file.write(yaml)
file.close()
file = open('Tuesday1.yaml', 'r')
clear=file.readlines()
str=''
clear.pop(0)
for i in clear:
    str=str+i
file = open('TuesdayDop1.yaml', 'w')
file.write(str)
file.close()
