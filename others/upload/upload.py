import requests
import sys

# 上传图片的目标URL
image_url = r'http://101.201.64.102:8890/damage_image/put/10'

# 文件路径
file_path = sys.argv[1]

# 损伤类型
damage_type = sys.argv[2] if len(sys.argv) > 2 else 1

# 创建文件对象
files = {'file': (file_path, open(file_path, 'rb'), 'png', {})}

# 发送请求
res = requests.request("POST", image_url, data={"type": "1"}, files=files)

print('图片已上传')

# 上传损伤信息的URL
upload_url = 'http://101.201.64.102:8890/damage/post'

post_data = {
  "location": "114.503931,38.083314",
  "type": damage_type,
  "description": "巡检车自动上传",
  "postId": 10,
  "source": 2
}

result = requests.request("POST", upload_url, data=post_data)

print('数据已上传')