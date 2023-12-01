UPDATE cityapi.cities
SET
  name = :name,
  country = :country,
  state = :state,
  region = :region,
  population = :population
WHERE id = :id