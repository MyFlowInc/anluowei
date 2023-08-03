import axios from 'axios'

export function upload(api, file) {
  var data = new FormData()
  data.append('file', file)
  const config = {
    headers: { 'Authorization': ""}
  }
  return axios.post(api, data, config)
}
