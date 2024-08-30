import pandas as pd
from geopy.geocoders import Nominatim
from tqdm import tqdm

geo_local = Nominatim(user_agent='South Korea')

def geocoding(address):
	# 주소가 유효하지 않은 경우
	if not address or pd.isna(address) or len(address.strip()) == 0:
		return None
	try:
		geo = geo_local.geocode(address)
		if geo:
			return [geo.latitude, geo.longitude]
		else:
			return None
	except:
		return None


# 데이터 불러오기
df = pd.read_csv('./toilet.csv', encoding='utf8', nrows=1000)
address = df['소재지도로명주소']

latitude = []
longitude = []

# tdqm 적용 및 경도, 위도 출력
for i in tqdm(address, desc="Processing address", unit="address"):
	geocode_result = geocoding(i);

	# 주소가 존재하지 않을 경우 WGS84 좌표 사용
	if geocode_result:
		lat, lng = geocode_result
	else:
		lat = df.loc[df['소재지도로명주소'] == i, 'WGS84위도'].values[0] if not pd.isna(df.loc[df['소재지도로명주소'] == i, 'WGS84위도'].values[0]) else 0
		lng = df.loc[df['소재지도로명주소'] == i, 'WGS84경도'].values[0] if not pd.isna(df.loc[df['소재지도로명주소'] == i, 'WGS84경도'].values[0]) else 0

	latitude.append(lat)
	longitude.append(lng)

# 결과 저장
address_df = pd.DataFrame({'화장실명': df['화장실명'], '소재지도로명주소': df['소재지도로명주소'], '위도': latitude, '경도': longitude})
address_df.to_csv('경위도_toilet.csv')