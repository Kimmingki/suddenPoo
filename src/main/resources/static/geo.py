import pandas as pd
from geopy.geocoders import Nominatim
from tqdm import tqdm

geo_local = Nominatim(user_agent='South Korea')

def geocoding(address):
	try:
		geo = geo_local.geocode(address)
		x_y = [geo.latitude, geo.longitude]
		return x_y
	except:
		return [0, 0]


# 데이터 불러오기
df = pd.read_csv('./toilet.csv', encoding='utf8')
address = df['소재지도로명주소']

latitude = []
longitude = []

# tdqm 적용 및 경도, 위도 출력
for i in tqdm(address, desc="Processing address", unit="address"):
	lat, lng = geocoding(i)
	latitude.append(lat)
	longitude.append(lng)

# 결과 저장
address_df = pd.DataFrame({'화장실명': df['화장실명'], '소재지도로명주소': df['소재지도로명주소'], '위도': latitude, '경도': longitude})
address_df.to_csv('경위도_toilet.csv')