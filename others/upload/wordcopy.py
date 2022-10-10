import os

file = 'R:\\GITHUB\\JancoBlog\\BackEnd\\jancoblogback\\src'

fp = open('front.txt', 'w+', encoding='utf-8')

def copyDirtext(dir):
  if os.path.isfile(dir):
    try:
      rf = open(dir, 'r', encoding='utf-8')
      text = rf.readlines()
      fp.write(dir)
      fp.write('\n\n\n')
      for line in text:
        fp.write(line)
      rf.close()
    except:
      pass
    
  else:
    files = os.listdir(dir)
    for file in files:
      copyDirtext(dir + '\\' + file)
  print(dir)

copyDirtext(file)

fp.close()