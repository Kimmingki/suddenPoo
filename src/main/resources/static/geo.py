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
df = pd.read_csv('./toilet.csv', encoding='utf8')

address = df['소재지도로명주소']
results = []

# tdqm 적용 및 경도, 위도 출력
for i in tqdm(address, desc="Processing address", unit="address"):
	geocode_result = geocoding(i);

	# 주소가 존재하지 않을 경우 WGS84 좌표 사용
	if geocode_result:
		lat, lng = geocode_result
	else:
		# WGS84 좌표값 확인
		wgs84_lat = df.loc[df['소재지도로명주소'] == i, 'WGS84위도']
		wgs84_lng = df.loc[df['소재지도로명주소'] == i, 'WGS84경도']

		# 값이 존재하고 NaN이 아닌 경우만 선택
		if not wgs84_lat.empty and not pd.isna(wgs84_lat.values[0]):
			lat = wgs84_lat.values[0]
		else:
			lat = 0

		if not wgs84_lng.empty and not pd.isna(wgs84_lng.values[0]):
			lng = wgs84_lng.values[0]
		else:
			lng = 0

	# 주소와 WGS84 모두 유효하지 않아 [0, 0]이 아닐 때만 결과에 추가
	if lat != 0 or lng != 0:
		results.append({'화장실명': df.loc[df['소재지도로명주소'] == i, '화장실명'].values[0],'소재지도로명주소': i, '위도': lat, '경도': lng})

# 결과 저장
address_df = pd.DataFrame(results)
address_df.to_csv('경위도_toilet.csv', index=False, encoding='utf-8-sig')